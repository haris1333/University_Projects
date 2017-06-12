#ifndef RENT_INFO_H
#define RENT_INFO_H


class rent_info
{
    public:
        rent_info();
        ~rent_info();
        int GetID() { return ID; }
        void SetID(int val) { ID = val; }
        int Getserial_number() { return serial_number; }
        void Setserial_number(int val) { serial_number = val; }
        int day;
        int month;
    protected:
    private:
        int ID;
        int serial_number;
};

#endif // RENT_INFO_H
