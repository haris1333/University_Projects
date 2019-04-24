#include <stdio.h>
#include <stdlib.h>

void convolve(int X[], int H[],int x_len,int h_len,int y_len) //convovlution function
{

    FILE *fp;
    int i=0;
    int *Y; //Array that stores the Y signal which is the result of the convolution of X and H signals

    Y = (int*) calloc(y_len,sizeof(int) ); //Allocating memory for the Y signal

    if(Y==NULL)
    {
       printf("Error allocating memory for Y.");
       exit(1);
    }


    for(i=0; i< y_len;i++) //Double-for loop in order to calculate the convolution of the signals X and H with totals.
    {
        int k, min_k, max_k; //min_k and max_k hold the lower and upper case , respectively, that the sum will use
        Y[i]=0;

        if(i>=h_len -1){ //if-else cases to determine the min_k
            min_k= i - (h_len -1) ;
        }else
        {
            min_k=0;
        }

        if(i < x_len -1){//if-else cases to determine the max_k
            max_k=i;
        }else
        {
            max_k=x_len -1;
        }

        for(k=min_k;k<=max_k;k++) //The sum that calculates the result of the convolution of X and H.
        {
            Y[i]+= X[k]*H[i-k];
        }

    }

    printf("The result convolultion of the previous signals is: "); //Printing of the result convolution signal
    for(i=0;i<y_len;i++)
    {
        printf("%d\n",Y[i]);
    }

    fp = fopen("y.txt","w");
    for(i=0;i<y_len;i++){ //Writing the result in the y.txt file
        fprintf(fp,"%d\n",Y[i]);
    }
    fclose(fp);


}


int main() //main function
{
    FILE *fp;


    int i=0,j=0;
    int counter=0;
    int y_len=0, x_len=0, h_len=0; //x_len = length of the X signal, h_len = length of the H signal, y_len = length of the convolultion result signal
    int *X; //array that stores the X signal
    int *H; //array that stores the H signal
    int k=1;

    fp=fopen("x.txt", "r"); //Reading the X file in order to determine the length of the X signal

    fscanf (fp, "%d", &i);
    while (!feof (fp))
    {
      counter++;
      fscanf (fp, "%d", &i);
    }
    fclose (fp);

    x_len=counter+1;//length of X



    X = (int*) calloc(counter+1,sizeof(int) ); //Allocating memory to store the X signal into the X array
    if (X==NULL)
    {
        printf ("Error allocating memory for X.");
        exit (1);
    }

    fp=fopen("x.txt", "r"); //Rereading the X file to store the X signal into the array we created above

    fscanf (fp, "%d", &X[0]);

    while (!feof (fp))
    {
      fscanf (fp, "%d", &X[k]);
      k++;
    }
    fclose(fp);

    printf("The first signal (x) given is: "); //Printing of the X signal

    for(j=0;j<x_len;j++){
        printf("%d\n",X[j]);
    }
    printf("End of signal X. \n");

    counter=0;
    fp=fopen("h.txt","r"); //Reading the H file to determine the length of the H signal

    fscanf (fp, "%d", &i);
    while (!feof (fp))
    {
      counter++;

      fscanf (fp, "%d", &i);
    }
    fclose (fp);
    h_len=counter+1; //length of H



    H = (int*) calloc(counter+1,sizeof(int) ); //Allocating memory to store the H signal into the H array
    if(H==NULL)
    {
        printf ("Error allocating memory for H.");
        exit(1);
    }

    fp=fopen("h.txt","r"); //Rereading the H file in order to store the H signal in the array we created above
    k=1;
    fscanf(fp, "%d", &H[0]);
    while (!feof (fp))
    {
      fscanf (fp, "%d", &H[k]);
      k++;
    }
    fclose(fp);

    printf("The second signal (h) given is: "); //Printing the H signal.
    for(j=0;j<h_len;j++){
        printf("%d\n",H[j]);
    }
    printf("End of signal H. \n");

    y_len = x_len+h_len-1; //Length of the convolution result signal

    convolve(X,H,x_len,h_len,y_len); //Calling of the convolve function

    return 0;
}
