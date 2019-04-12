#include <stdio.h>
#define N 20
#define V 12
#define S 70
#define T 10

long long c,i,f;

void go(int s,int v,int n,long long p,int k) {
    if (n==N) {
         if (s==S) c+=f/p;
         return;
     }
     go(s+(N-n<=T)*v,v,n+1,p*(k+1),k+1);
     for (v+=1;v<=V;v++)
         go(s+(N-n<=T)*v,v,n+1,p,1);
 }

int main() {
     for (f=i=1;i<=N;i++) f*=i;
     go(0,1,0,1LL,0);
     printf("%lli\n",c);
}