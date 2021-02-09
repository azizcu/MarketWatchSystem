package com.example.marketwatchsystem.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    private int localPrice=0,internationalPrice=0,supply=0,demand=0,
            importedPrice=0,importedMoney=0,depositeMoney=0;
    public static final String DATABASE_NAME = "MyDBName.db";

    public static final String TABLE_NAME_DISTRIBUTE_INFO = "DistibuteInfo";
    public static final String TABLE_NAME_AGRI = "Agri";
    public static final String TABLE_NAME_NBR = "Nbr";
    public static final String TABLE_NAME_TCB = "Tcb";
    public static final String TABLE_NAME_CCI = "Cci";

    public static final String AGRI_COLUMN_NAME = "productName";
    public static final String AGRI_COLUMN_CODE = "productCode";
    public static final String AGRI_COLUMN_SUPPLY = "supply";

    public static final String DISTRIBUTE_INFO_COLUMN_NAME = "productName";
    public static final String DISTRIBUTE_INFO_COLUMN_CODE = "productCode";
    public static final String DISTRIBUTE_INFO_COLUMN_DEPARTMENT = "department";
    public static final String AGRI_COLUMN_DEMAND = "demand";
    public static final String AGRI_COLUMN_PRODUCTION = "production";
    public static final String AGRI_COLUMN_SOURCE = "source";

    public static final String NBR_COLUMN_NAME = "productName";
    public static final String NBR_COLUMN_CODE = "productCode";
    public static final String NBR_COLUMN_PRICE = "price";

    public static final String TCB_COLUMN_NAME = "productName";
    public static final String TCB_COLUMN_CODE = "productCode";
    public static final String TCB_COLUMN_LOCAL_PRICE = "loaclPrice";
    public static final String TCB_COLUMN_INTER_PRICE = "internationalPrice";

    public static final String CCI_COLUMN_NAME = "productName";
    public static final String CCI_COLUMN_CODE = "productCode";
    public static final String CCI_COLUMN_IMPORTED_PRICE = "importedPrice";
    public static final String CCI_COLUMN_DEPOSITE_PRICE = "depositePrice";
    public static final String CCI_COLUMN_IMPORTER_PRICE = "importerPrice";
    public static final String CCI_COLUMN_IMPORTER_BANK_AC_NO = "importerBankAcNo";

    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "place";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table contacts " +
                        "(id integer primary key, name text,phone text,email text, street text,place text)"
        );

        //Create DistibuteInfo
        db.execSQL(
                "create table DistibuteInfo " +
                        "(productCode integer primary key,productName text,department text)"
        );

        //Create Agri
        db.execSQL(
                "create table Agri" +
                        "(productCode integer primary key,productName text,supply integer,demand integer,source text,production integer)"
        );

        //Create Nbr
        db.execSQL(
                "create table Nbr " +
                        "(productCode integer primary key, productName text,price integer)"
        );

        //Create Tcb
        db.execSQL(
                "create table Tcb " +
                        "(productCode integer primary key, productName text,loaclPrice integer,internationalPrice integer)"
        );

        //Create Cci
        db.execSQL(
                "create table Cci " +
                        "(productCode integer primary key, productName text,importedPrice integer,depositePrice integer,importerPrice integer,importerBankAcNo integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        db.execSQL("DROP TABLE IF EXISTS Agri");
        db.execSQL("DROP TABLE IF EXISTS Nbr");
        db.execSQL("DROP TABLE IF EXISTS Tcb");
        db.execSQL("DROP TABLE IF EXISTS Cci");
        onCreate(db);
    }

    public boolean insertContact (String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.insert("contacts", null, contentValues);
        return true;
    }


    //Insert
    public boolean insertDistributeInfo (String name, String code, String dept) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DISTRIBUTE_INFO_COLUMN_NAME, name);
        contentValues.put(DISTRIBUTE_INFO_COLUMN_CODE, code);
        contentValues.put(DISTRIBUTE_INFO_COLUMN_DEPARTMENT,dept);
        db.insert("DistibuteInfo", null, contentValues);
        return true;
    }

    public boolean insertAgri (String name,String code, String supply, String demand,String source,String production) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AGRI_COLUMN_NAME, name);
        contentValues.put(AGRI_COLUMN_CODE,code);
        contentValues.put(AGRI_COLUMN_SUPPLY, supply);
        contentValues.put(AGRI_COLUMN_DEMAND, demand);
        contentValues.put(AGRI_COLUMN_SOURCE, source);
        contentValues.put(AGRI_COLUMN_PRODUCTION,production);
        db.insert(TABLE_NAME_AGRI, null, contentValues);
        return true;
    }

    public boolean insertNbr (String name, String code, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NBR_COLUMN_NAME, name);
        contentValues.put(NBR_COLUMN_CODE, code);
        contentValues.put(NBR_COLUMN_PRICE,price);
        db.insert(TABLE_NAME_NBR, null, contentValues);
        return true;
    }

    public boolean insertTcb (String name, String code, String localprice, String interprice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TCB_COLUMN_NAME, name);
        contentValues.put(TCB_COLUMN_CODE, code);
        contentValues.put(TCB_COLUMN_LOCAL_PRICE, localprice);
        contentValues.put(TCB_COLUMN_INTER_PRICE, interprice);
        db.insert(TABLE_NAME_TCB, null, contentValues);
        return true;
    }

    public boolean insertCci (String name, String code, String importedPrice, String depositePrice,String importerPrice,String importerBankAcNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CCI_COLUMN_NAME, name);
        contentValues.put(CCI_COLUMN_CODE, code);
        contentValues.put(CCI_COLUMN_IMPORTED_PRICE, importedPrice);
        contentValues.put(CCI_COLUMN_DEPOSITE_PRICE, depositePrice);
        contentValues.put(CCI_COLUMN_IMPORTER_PRICE, importerPrice);
        contentValues.put(CCI_COLUMN_IMPORTER_BANK_AC_NO,importerBankAcNo);
        db.insert(TABLE_NAME_CCI, null, contentValues);
        return true;
    }

    //Update
    public boolean updateAgri (String name, String code, String supply, String demand,String source,String production) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AGRI_COLUMN_NAME, name);
        contentValues.put(AGRI_COLUMN_CODE, code);
        contentValues.put(AGRI_COLUMN_SUPPLY, supply);
        contentValues.put(AGRI_COLUMN_DEMAND, demand);
        contentValues.put(AGRI_COLUMN_SOURCE, source);
        contentValues.put(AGRI_COLUMN_PRODUCTION,production);
        db.update(TABLE_NAME_AGRI, contentValues, "productCode = ?", new String[] { code } );
        return true;
    }

    public boolean updateNbr (String name, String code, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NBR_COLUMN_NAME, name);
        contentValues.put(NBR_COLUMN_CODE, code);
        contentValues.put(NBR_COLUMN_PRICE,price);
        db.update(TABLE_NAME_NBR, contentValues, "productCode = ?", new String[] { code } );
        return true;
    }

    public boolean updateTcb (String name, String code, String localprice, String interprice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TCB_COLUMN_NAME, name);
        contentValues.put(TCB_COLUMN_CODE, code);
        contentValues.put(TCB_COLUMN_LOCAL_PRICE, localprice);
        contentValues.put(TCB_COLUMN_INTER_PRICE, interprice);
        db.update(TABLE_NAME_TCB, contentValues, "productCode = ?", new String[] { code } );
        return true;
    }

    public boolean updateCci (String name, String code, String importedPrice, String depositePrice,String importerPrice,String importerBankAcNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CCI_COLUMN_NAME, name);
        contentValues.put(CCI_COLUMN_CODE, code);
        contentValues.put(CCI_COLUMN_IMPORTED_PRICE, importedPrice);
        contentValues.put(CCI_COLUMN_DEPOSITE_PRICE, depositePrice);
        contentValues.put(CCI_COLUMN_IMPORTER_PRICE, importerPrice);
        contentValues.put(CCI_COLUMN_IMPORTER_BANK_AC_NO,importerBankAcNo);
        db.update(TABLE_NAME_CCI, contentValues, "productCode = ?", new String[] { code } );
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

   // public int numberOfRows(){
        //SQLiteDatabase db = this.getReadableDatabase();
       // int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
       // return numRows;
    //}

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllProductNames() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from DistibuteInfo", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(DISTRIBUTE_INFO_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllProductCodes() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from DistibuteInfo", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(DISTRIBUTE_INFO_COLUMN_CODE)));
            res.moveToNext();
        }
        return array_list;
    }


    public String getMessage(String productCode) {

        final String MY_QUERY = "SELECT Agri.supply,Agri.demand,Tcb.loaclPrice,Tcb.internationalPrice,Nbr.price,Cci.importedPrice,Cci.depositePrice FROM Agri INNER JOIN Tcb ON Agri.productCode=Tcb.productCode  INNER JOIN Nbr ON Tcb.productCode=Nbr.productCode INNER JOIN Cci ON Cci.productCode = Nbr.productCode WHERE Agri.productCode=?";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( MY_QUERY, new String[]{String.valueOf(productCode)} );
        res.moveToFirst();

        supply = Integer.valueOf(res.getString(res.getColumnIndex(AGRI_COLUMN_SUPPLY)));
        demand = Integer.valueOf(res.getString(res.getColumnIndex(AGRI_COLUMN_DEMAND)));
        importedPrice = Integer.valueOf(res.getString(res.getColumnIndex(NBR_COLUMN_PRICE)));
        localPrice = Integer.valueOf(res.getString(res.getColumnIndex(TCB_COLUMN_LOCAL_PRICE)));
        internationalPrice = Integer.valueOf(res.getString(res.getColumnIndex(TCB_COLUMN_INTER_PRICE)));
        importedMoney = Integer.valueOf(res.getString(res.getColumnIndex(CCI_COLUMN_IMPORTED_PRICE)));
        depositeMoney = Integer.valueOf(res.getString(res.getColumnIndex(CCI_COLUMN_DEPOSITE_PRICE)));

        if(localPrice-internationalPrice>=10){
            if(supply-demand>=0){
                return "Inconsistency arise in local market warehouse";
            }
            else{
               if(localPrice-importedPrice>=10){
                   if(importedMoney>depositeMoney){
                       return "Inconsistency arise by importer";
                   }
                   else{
                      return "Inconsistency arise in local market warehouse";
                   }
               }
               else{
                   return "Everything is ok";
               }
            }
        }
        else{
            return "Everything is ok";
        }
    }


}