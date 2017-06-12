#ifndef SERIES_H
#define SERIES_H

#include <movies.h>


class series : public movies
{
    public:
        series();
        ~series();
        int Getseason() { return season; }
        void Setseason()
        {
            cout<<"Give the number of the current season: "<<endl;
            cin>>season;
        }
        void Copyseason(int number){season=number;}
        int Getfirst_ep() { return first_ep; }
        void Setfirst_ep()
        {
            cout<<"Give the number of the first episode: "<<endl;
            cin>>first_ep;
        }
        void Copyfirst_ep(int number){first_ep=number;}
        int Getfinal_ep() { return final_ep; }
        void Setfinal_ep()
        {
            cout<<"Give the number of the final episode: "<<endl;
            cin>>final_ep;
        }
        void Copyfinal_ep(int number){final_ep=number;}
    protected:
    private:
        int season;
        int first_ep;
        int final_ep;
};

#endif // SERIES_H
