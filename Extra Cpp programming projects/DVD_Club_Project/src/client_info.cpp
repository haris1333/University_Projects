#include "client_info.h"

client_info::client_info()
{
    for (int i=0;i<10;i++)//ctor
    {
        first_name[i]=' ';
        last_name[i]=' ';
    }
}


client_info::~client_info()
{
    //dtor
}
