#include "VIP_client_info.h"

VIP_client_info::VIP_client_info()
{
   for(int i=0;i<10;i++) //ctor
      bank_name[i]=' ';
}

VIP_client_info::~VIP_client_info()
{
    //dtor
}
