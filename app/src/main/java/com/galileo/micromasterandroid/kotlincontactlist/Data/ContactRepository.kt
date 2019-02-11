package com.galileo.micromasterandroid.kotlincontactlist.Data

import android.app.Application
import android.os.AsyncTask
import android.arch.lifecycle.LiveData
import com.galileo.micromasterandroid.kotlincontactlist.DataModels.Contact


class ContactRepository(application: Application){

    private val contactDao: ContactDAO
    private val listLiveData: LiveData<List<Contact>>

    init{
        val contactDatabase = ContactDatabase.getInstance(application)
        contactDao = contactDatabase?.contactDAO()!!
        listLiveData = contactDao.getAll()
    }

    fun getAllContacts(): LiveData<List<Contact>>{
        return listLiveData;
    }

    fun insert(contact: Contact){
        insertAsyncTask(contactDao).execute(contact)
    }

    fun deleteAll(){
        deleteAsyncTask(contactDao).execute()
    }

    private class insertAsyncTask
        internal constructor(private val mAsyncContactDAO: ContactDAO) : AsyncTask<Contact, Void, Void>() {

        override fun doInBackground(vararg params: Contact): Void? {
            mAsyncContactDAO.insert(params[0])
            return null
        }
    }
    private class deleteAsyncTask
        internal constructor(private val mAsyncContactDAO: ContactDAO) :AsyncTask<Void,Void,Void>(){

        override fun doInBackground(vararg p0: Void?): Void? {
            mAsyncContactDAO.deleteAll()
            return null
        }
    }
}