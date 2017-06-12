#ifndef CLIENT_INFO_H
#define CLIENT_INFO_H
#include <cstddef>
#include <iostream>
#include <fstream>
#include <string>
#include <string.h>

using namespace std;

class client_info
{
    public:
        client_info();
        ~client_info();
        int Getclient_ID() {return client_ID; }
        void Setclient_ID()
        {
            int val;
            cout<<"Give client's ID: "<<endl;
            cin>>val;
            client_ID = val;
        }
        void Copyclient_ID(int val) {client_ID=val;}
        char *Getfirst_name() { return first_name; }
        void Setfirst_name()
        {
            cin.ignore();
            cout<<"Give client's first name: "<<endl;
            gets(first_name);
        }
        void Copyfirst_name(char client_name[])
        {

            for(int i=0;i<10;i++)
                first_name[i]=client_name[i];
        }
        char *Getlast_name() { return last_name; }
        void Setlast_name()
        {
            cin.ignore();
            cout<<"Give client's last name: "<<endl;
            gets(last_name);
        }
        void Copylast_name(char c_name[])
        {
            for(int i=0;i<10;i++)
                last_name[i]=c_name[i];
        }
        int Getb_day() { return b_day; }
        void Setb_date()
        {
            cin.ignore();
            cout<<"Give client's birth date: "<<endl;
            cin>>b_day>>b_month>>b_year;
            cin.ignore();
        }
        void Copyb_day(int day) {b_day=day;}
        void Copyb_month(int month) {b_month=month;}
        void Copyb_year (int year) {b_year=year;}
        int Getb_month() { return b_month; }
        int Getb_year() { return b_year; }
        char Getgen() { return gen; }
        void Setgen()
        {
            cin.ignore();
            cout<<"Give client's gender (M or F): "<<endl;
            cin>>gen;
        }
        void Copygen(char g) {gen=g;}
        int Getphone_number() { return phone_number; }
        void Setphone_number()
        {
            cin.ignore();
            cout<<"Give client's phone number: "<<endl;
            cin>>phone_number;
        }
        void Copyphone_number(int number) {phone_number=number;}
        string Getaddress() {return address;}
        void Setaddress()
        {
            cin.ignore();
            cout<<"Give the client's address: "<<endl;
            getline(cin,address);
        }
        void Copyaddress(string ad) {address=ad;}
    protected:
    private:
        int client_ID;
        char first_name[10];
        char last_name[10];
        string address;
        int b_day;
        int b_month;
        int b_year;
        char gen;
        int phone_number;
};

#endif // CLIENT_INFO_H
