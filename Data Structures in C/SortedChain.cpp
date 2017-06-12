#include "SortedChain.h"
#include <iostream>

using namespace std;

SortedChain::SortedChain()
{
    first=0;
}

SortedChain::~SortedChain()
{
    //dtor
}

SortedChain& SortedChain::Delete(int x)
{
    SortedChainNode *p=first;
    SortedChainNode *prevp=0;

    for (; p && p->data<x; prevp=p, p=p->link);  //������ ��� �� k ��� data

    if(p && p->data==x)  //�� �� ����
    {
        if (prevp)
            prevp->link=p->link;  //������� �� p ��� ��� �������
        else
            first=p->link;

        delete p;
        return *this;
    }
    else
        cout<<endl<<"No match found."<<endl;
}

SortedChain& SortedChain::insert_chainLink(int e,int w)
{
   SortedChainNode *p=first;
   SortedChainNode *prevp=0;

   for(; p && p->data<e; prevp=p, p=p->link);  //���������� ��� tp ���� �� ��������� �� e ���� ��� �� tp

   if(p && p->data==e)  //�� �� e ������� ���
   {
       cout<<endl<<"This number already exists."<<endl;
       return *this;
   }
   else  //�� ��� �������, ��������� ����� ��� �� e
   {
       SortedChainNode *node=new SortedChainNode;
       node->data=e;
       node->key=w;
       node->link=p;  //����� ��� ����� ���� �� tp

       if(prevp)
        prevp->link=node;
       else
        first=node;
   }
   return *this;
}

void SortedChain::Setf_data(int e)
{
    f_data=e;
}

int SortedChain::Getf_data()
{
    return f_data;
}

void SortedChain::printAll(int num)
{
    SortedChainNode *p=first;
    cout<<num<<endl;
    while(p!=NULL)
    {
        cout<<p->data<<endl;
        p=p->link;
    }
}
