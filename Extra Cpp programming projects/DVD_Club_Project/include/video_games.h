#ifndef VIDEO_GAMES_H
#define VIDEO_GAMES_H
#include <cstddef>
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

class video_games
{
    public:
        video_games();
        ~video_games();
        int GetID() { return ID; }
        void SetID()
        {
            cout<<"Give the game's ID: "<<endl;
            cin>>ID;
        }
        void CopyID(int val){ID=val;}
        int Getserial_number() { return serial_number; }
        void Setserial_number()
        {
            cout<<"Give the game's serial number: "<<endl;
            cin>>serial_number;
        }
        void Copyserial_number(int number) {serial_number=number;}
        string Gettitle() { return title; }
        void Settitle()
        {
            cout<<"Give the game's title: "<<endl;
            getline(cin,title);
        }
        void Copytitle(string name){title=name;}
        string Gettype() { return type; }
        void Settype()
        {
            cout<<"Give the game's type: "<<endl;
            getline(cin,type);
        }
        void Copytype(string name){type=name;}
        string Getconsole_name() { return console_name; }
        void Setconsole_name()
        {
            cin.ignore();
            cout<<"Give the game's console requirement: "<<endl;
            getline(cin,console_name);
        }
        void Copyconsole_name(string name){console_name=name;}
        int Getyear_pub() { return year_pub; }
        void Setyear_pub()
        {
            cout<<"Give the year the game was published: "<<endl;
            cin>>year_pub;
        }
        void Copyyear_pub(int year){year_pub=year;}
        int Getnum_of_copies() { return num_of_copies; }
        void Setnum_of_copies()
        {
            cout<<"Give the number of the remaining copies: "<<endl;
            cin>>num_of_copies;
        }
        void Copynum_of_copies(int number){num_of_copies=number;}
        int num_of_copies;
    protected:
    private:
        int ID;
        int serial_number;
        string title;
        string type;
        string console_name;
        int year_pub;
};

#endif // VIDEO_GAMES_H
