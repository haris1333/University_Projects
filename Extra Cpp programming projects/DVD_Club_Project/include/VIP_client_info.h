#ifndef VIP_CLIENT_INFO_H
#define VIP_CLIENT_INFO_H

#include <client_info.h>


class VIP_client_info : public client_info
{
    public:
        VIP_client_info();
        ~VIP_client_info();
        int Getcard_number() { return card_number; }
        void Setcard_number()
        {
            cin.ignore();
            cout<<"Give VIP client's credit card number: "<<endl;
            cin>>card_number;
        }
        void Copycard_number(int number) {card_number=number;}
        char *Getbank_name() { return bank_name; }
        void Setbank_name()
        {
            cin.ignore();
            cout<<"Give VIP client's bank name: "<<endl;
            gets(bank_name);
        }
        void Copybank_name(char name[])
        {
            for(int i=0;i<10;i++)
                bank_name[i]=name[i];
        }
        int GetCVN() { return CVN; }
        void SetCVN()
        {
            int code;
            cout<<"Give VIP client's CVN code: "<<endl;
            do
            {
                cin.ignore();
                cin>>code;
                if (code<0 || code>999) cout<<"Wrong CVN code. Try again."<<endl;
            }
            while (code<0 || code>999);
            CVN=code;
        }
        void CopyCVN(int code){CVN=code;}
    protected:
    private:
        int card_number;
        char bank_name[10];
        int CVN;
};

#endif // VIP_CLIENT_INFO_H
