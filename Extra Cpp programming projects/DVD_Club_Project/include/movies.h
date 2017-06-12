#ifndef MOVIES_H
#define MOVIES_H
#include <cstddef>
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

class movies
{
    public:
        movies();
        ~movies();
        int Getserial_number() { return serial_number; }
        void Setserial_number()
        {
            cout<<"Give its serial number: "<<endl;
            cin>>serial_number;
        }
        void Copyserial_number(int number) {serial_number=number;}
        int GetID() { return ID; }
        void SetID()
        {
            cout<<"Give its ID: "<<endl;
            cin>>ID;
        }
        void CopyID(int val){ID=val;}
        string Gettitle() { return title; }
        void Settitle()
        {
            cin.ignore();
            cout<<"Give its title: "<<endl;
            getline(cin,title);
        }
        void Copytitle(string name){title=name;}
        string Gettype() { return type; }
        void Settype()
        {
            cin.ignore();
            cout<<"Give the type of the movie or series: "<<endl;
            getline(cin,type);
        }
        void Copytype(string name){type=name;}
        string Getdirector() { return director; }
        void Setdirector()
        {
            cin.ignore();
            cout<<"Give the director's name: "<<endl;
            getline(cin,director);
        }
        void Copydirector(string name){director=name;}
        string Getactors() { return actors; }
        void Setactors()
        {
            cin.ignore();
            cout<<"Give the actors' name: "<<endl;
            getline(cin,actors);
        }
        void Copyactors(string name){actors=name;}
        int Getduration() { return duration; }
        void Setduration()
        {
            cout<<"Give its duration: "<<endl;
            cin>>duration;
        }
        void Copyduration(int val) {duration=val;}
        int Getpub_year() { return pub_year; }
        void Setpub_year()
        {
            cout<<"Give the year it was published: "<<endl;
            cin>>pub_year;
        }
        void Copypub_year(int year){pub_year=year;}
        int Getnum_of_copies() { return num_of_copies; }
        void Setnum_of_copies()
        {
            cout<<"Give the number of the remaining copies: "<<endl;
            cin>>num_of_copies;
        }
        void Copynum_of_copies(int number){num_of_copies=number;}
        bool isDVD()
        {
            char ap;
            cout<<"Press d if you want it on DVD or b if you want it on Blu-Ray"<<endl;
            cin>>ap;
            if (ap=='d') return true; else return false;
        }
        int num_of_copies;
    protected:
    private:
        int serial_number;
        int ID;
        string title;
        string type;
        string director;
        string actors;
        int duration;
        int pub_year;
};

#endif // MOVIES_H
