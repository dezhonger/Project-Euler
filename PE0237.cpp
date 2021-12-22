/*
ZOJ 3256
N*M(2<=N<=7,1<=M<=10^9)的方格，问从左上角的格子到左下角的格子，
而且仅经过所有格子一次的路径数
插头DP+矩阵加速

对于一个图的邻接矩阵的N次方，其中(i,j)位置上的元素表示
点i经过N步到达点ｊ的方案数

http://oeis.org/A181688
*/
#include<stdio.h>
#include<iostream>
#include<algorithm>
#include<string.h>
using namespace std;
// #define int long long
typedef long long LL;

const int STATE=1010;
const int HASH=419;//这个小一点，效率高
const int MOD=100000000;

LL N,M;
int D;
int code[10];
int ch[10];
int g[200][200];//状态转移图

struct Matrix
{
    LL n,m;
    int mat[200][200];
};
Matrix mul(Matrix a,Matrix b)//矩阵相乘，要保证a的列数和b的行数相等
{
    Matrix ret;
    ret.n=a.n;
    ret.m=b.m;
    long long sum;
    for(int i=0;i<a.n;i++)
       for(int j=0;j<b.m;j++)
       {
           sum=0;
           for(int k=0;k<a.m;k++)
           {
               sum+=(long long)a.mat[i][k]*b.mat[k][j];
               //sum%=MOD;//加了这句话就会TLE，坑啊。。。
           }
           ret.mat[i][j]=sum%MOD;
       }
    return ret;
}
Matrix pow_M(Matrix a,LL n)//方阵的n次方
{
    Matrix ret=a;
    memset(ret.mat,0,sizeof(ret.mat));
    for(int i=0;i<a.n;i++)ret.mat[i][i]=1;//单位阵
    Matrix temp=a;
    while(n)
    {
        if(n&1)ret=mul(ret,temp);
        temp=mul(temp,temp);
        n>>=1;
    }
    return ret;
}

struct HASHMAP
{
    int head[HASH],next[STATE],size;
    int state[STATE];
    void init()
    {
        size=0;
        memset(head,-1,sizeof(head));
    }
    int push(int st)
    {
        int i,h=st%HASH;
        for(i=head[h];i!=-1;i=next[i])
           if(state[i]==st)
              return i;
        state[size]=st;
        next[size]=head[h];
        head[h]=size++;
        return size-1;
    }
}hm;
void decode(int *code,int n,int st)
{
    for(int i=n-1;i>=0;i--)
    {
        code[i]=st&3;
        st>>=2;
    }
}
int encode(int *code,int n)
{
    int cnt=1;
    memset(ch,-1,sizeof(ch));
    ch[0]=0;
    int st=0;
    for(int i=0;i<n;i++)
    {
        if(ch[code[i]]==-1)ch[code[i]]=cnt++;
        code[i]=ch[code[i]];
        st<<=2;
        st|=code[i];
    }
    return st;
}

bool check(int st,int nst)//判断两种状态能不能转移
{
    decode(code,N,st);
    int flag=0;//标记格子上边是否有插头
    int cnt=0;
    int k;
    for(int i=0;i<N;i++)
    {
        if(flag==0)//这个格子上边没有插头
        {
            if(code[i]==0&&(nst&(1<<i))==0)//左边和右边都没有插头
               return false;
            if(code[i]&&(nst&(1<<i)))continue;
            if(code[i])flag=code[i];//插头从左边过来，从下边出去
            else flag=-1;//插头从下边进来从右边出去
            k=i;
        }
        else
        {
            if(code[i]&&(nst&(1<<i)))//左边和右边和上边都有插头
               return false;
            if(code[i]==0&&(nst&(1<<i))==0)continue;
            if(code[i])
            {
                if(code[i]==flag&&((nst!=0)||i!=N-1))return false;//只有最后一个格子才能合起来
                if(flag>0)
                {
                    for(int j=0;j<N;j++)
                      if(code[j]==code[i]&&j!=i)
                          code[j]=code[k];
                    code[i]=code[k]=0;
                }
                else
                {
                    code[k]=code[i];
                    code[i]=0;
                }
            }
            else
            {
                if(flag>0)code[i]=code[k],code[k]=0;
                else code[i]=code[k]=N+(cnt++);
            }
            flag=0;
        }
    }
    if(flag!=0)return false;
    return true;
}
struct Node
{
    int g[200][200];
    LL D;
}node[20];//打表之用
void init()
{
    if(node[N].D!=0)
    {
        memcpy(g,node[N].g,sizeof(node[N].g));
        D=node[N].D;
        return;
    }
    int st,nst;
    hm.init();
    memset(code,0,sizeof(code));
    code[0]=code[N-1]=1;
    hm.push(0);
    hm.push(encode(code,N));
    memset(g,0,sizeof(g));
    for(int i=1;i<hm.size;i++)
    {
        st=hm.state[i];
        for(nst=0;nst<(1<<N);nst++)
          if(check(st,nst))
          {
              int j=hm.push(encode(code,N));
              g[i][j]=1;
          }
    }
    D=hm.size;
    memcpy(node[N].g,g,sizeof(g));
    node[N].D=D;
}
void solve()
{
    Matrix temp;
    temp.n=temp.m=D;
    memcpy(temp.mat,g,sizeof(g));
    Matrix ans=pow_M(temp,M);
    if(ans.mat[1][0]==0) cout << "Impossible\n" << endl;
    // else printf("%d\n",ans.mat[1][0]%MOD);
    cout << ans.mat[1][0]%MOD << endl;
}
signed main()
{
    //freopen("in.txt","r",stdin);
    //freopen("out.txt","w",stdout);
    for(int i=0;i<20;i++)node[i].D=0;
    N = 4;
    M=1000000000000;
    // M=1000000000000;
    init();
    solve();
    return 0;
}
