#include <iostream>
#include <string>


using namespace std;

#define WIDTH  8
#define HEIGHT  8


class State
{
    int robX,robY;
    bool free[WIDTH][HEIGHT];
    int pathSize;
    string *path;
  public:
     bool lights;

    State()
    {
        pathSize=0;
        path=NULL;
    }
    State(int X, int Y)
    {
        robX=X;
        robY=Y;
        lights=true;

        for (int i=0;i<WIDTH;i++)
            for (int j=0;j<HEIGHT;j++)
                free[i][j]=true;
        pathSize = 0;
        path=NULL;
    }
    State (const State &old)
    {
        robX=old.robX;
        robY=old.robY;
        for (int i=0;i<WIDTH;i++)
            for (int j=0;j<HEIGHT;j++)
                free[i][j]=old.free[i][j];
        pathSize=old.pathSize;
        path = new string[pathSize];
        for (int i=0;i<pathSize;i++)
            path[i]=old.path[i];
    }
    void addAction (string a)
    {
        string *temp;
        int i;
        pathSize++;
        temp = new string[pathSize];
        for(i=0;i<pathSize-1;i++)
            temp[i]=path[i];
        temp[i]=a;
        if (path!=NULL)
            delete[] path;
        path=temp;
    }
    void print()
    {
        int i;
        cout<<"(rX,rY) = ("<<robX<<", "<<robY<<") path: ";
        if (pathSize>0)
        {
            for(i=0;i<pathSize-1;i++)
                cout<<path[i]<<", ";
            cout<<path[i];
        }
        cout<<endl;
    }
    void setFree(int i, int j, bool f)
    {
        free[j][i]=f;
    }
    int getY()
    {
        return robY;
    }
    int getX()
    {
        return robX;
    }
    bool isFree(int x,int y)
    {
        return free[x][y];
    }
    void setX(int x)
    {
        robX=x;
    }
    void setY(int y)
    {
        robY=y;
    }
    bool equals (State s)
    {
        if (robX==s.robX && robY==s.robY)
            return true;
        return false;
    }
    State operator= (State o)
    {
        robX = o.robX;
        robY = o.robY;
        pathSize= o.pathSize;
        delete[] path;
        path = new string[pathSize];
        for (int i =0;i<pathSize;i++)
            path[i]=o.path[i];
        for (int i=0;i<WIDTH;i++)
            for (int j=0;j<HEIGHT;j++)
                free[i][j]= o.free[i][j];
        return *this;
    }
};

class node
{
  public:
    State data;
    node *next;
};
class Stack
{
  protected:
    node *head;
  public:
    Stack()
    {
        head=NULL;
    }
    ~Stack()
    {
        node *t;
        while (head!=NULL)
        {
            t=head;
            head=head->next;
            delete(t);
        }
    }

    bool push (State s)
    {
        node *a;
        a = new node;
        if (a==NULL)
            return false;
        a->data=s;
        a->next=head;
        head = a;
        return true;
    }
    bool pop (State &s)
    {
        if (head==NULL)
            return false;
        s=head->data;
        node *temp = head;
        head= head->next;
        delete temp;
        return true;
    }
    bool isEmpty()
    {
        return (head==NULL);
    }
};

class Set:private Stack
{
  public:
    Set():Stack()
    {
    }
    bool find (State s)
    {
        node *t=head;
        while (t!=NULL)
        {
            if (t->data.equals(s))
                return true;
            t=t->next;
        }
        return false;
    }
    bool insert(State s)
    {
        if (!find(s))
            return push(s);
        return true;
    }
};

bool turnOn(State s, State &n)
{
    if (!s.lights)
    {
//        cout<<"lights off"<<endl;
        n=s;
        n.lights=true;
        n.addAction("TO");
        return true;
    }
    return false;
}

bool turnOff (State s, State &n)
{
    if (s.lights)
    {
        n=s;
        n.lights=false;
        n.addAction("TF");
        return true;
    }
    return false;
}
bool goUp(State s, State &n)
{
    if (s.getY()>1 && s.isFree(s.getX(),s.getY()-1))
    {
        n=s;
        n.setY(n.getY()-1);
        n.addAction("Up");
        return true;
    }
    return false;
}

bool goDown(State s, State &n)
{
    if (s.getY()<HEIGHT && s.isFree(s.getX(),s.getY()+1))
    {
        n=s;
        n.setY(n.getY()+1);
        n.addAction("Down");
        return true;
    }
    return false;
}
bool goLeft(State s, State &n)
{
    if (s.getX()>1 && s.isFree(s.getX()-1,s.getY()))
    {
        n=s;
        n.setX(n.getX()-1);
        n.addAction("Left");

        return true;
    }
    return false;
}
bool goRight(State s, State &n)
{
    if (s.getX()<WIDTH&& s.isFree(s.getX()+1,s.getY()))
    {
        n=s;
        n.setX(n.getX()+1);
        n.addAction("Right");
        return true;
    }
    return false;
}

void expand(const State &s, State* &children, int &num)
{
    State u,d,l,r,a,b;
    int i=0;
    num=0;
    if (goUp(s,u))
        num++;
    if (goDown(s,d))
        num++;
    if (goLeft(s,l))
        num++;
    if (goRight(s,r))
        num++;
/*    if (turnOn(s,a))
        num++;
    if (turnOff(s,b))
        num++;
*/
    children = new State[num];
    if (goUp(s,u))
    {
        children[i++]=u;
        //cout<<"up"<<endl;
    }
    if (goDown(s,d))
    {
        children[i++]=d;
        //cout<<"down"<<endl;
    }
    if (goLeft(s,l))
    {
        //cout<<"left"<<endl;
        children[i++]=l;
    }
    if (goRight(s,r))
    {
        //cout<<"right"<<endl;
        children[i++]=r;
    }
/*
    if (turnOn(s,a))
    {
        cout<<"on"<<endl;
        children[i++]=a;
    }
    else
        cout<<"no"<<endl;
    if (turnOff(s,b))
    {
        cout<<"of"<<endl;
        children[i++]=b;
    }
*/
//cout<<"finish"<<endl;
}

bool DFS(Stack &agenda, Set &closed,const State &goal,State &solution)
{
    State s;
    if (agenda.isEmpty())
        return false;
    agenda.pop(s);
    //s.print();
    if (s.equals(goal))
    {
        solution = s;
        return true;
    }
    if (!closed.find(s))
    {
        State *children;
        int num;
//        cout<<"checking for children"<<endl;
        expand(s,children,num);
        //cout<<num<<" children"<<endl;
        for (int i=0;i<num;i++)
            agenda.push(children[i]);
        closed.insert(s);
    }
    return DFS(agenda,closed,goal,solution);
}


int main()
{
    Stack agenda;
    Set closed;
    State initial(2,2);
    initial.lights=true;
    initial.setFree(2,6,false);
    initial.setFree(3,5,false);
    initial.setFree(3,6,false);
    initial.setFree(4,7,false);
    initial.setFree(5,5,false);
    initial.setFree(5,6,false);
    initial.setFree(6,5,false);
    initial.setFree(7,2,false);
    initial.setFree(7,3,false);
    initial.setFree(7,4,false);
    initial.setFree(7,5,false);
    State goal(7,8);
    goal.lights=false;
    agenda.push(initial);

    State solution;
//    cout<<"Entering DFS"<<endl;
    DFS(agenda,closed,goal,solution);
    cout<<"Solution "<<endl;
    solution.print();

    return 0;
}
