#include <iostream>
#include <vector>
using namespace std;

//十个数的和的范围是sum(0,0,1,1,2,2,3,3,4,4) = 20
// max = sum(5,5,6,6,7,7,8,8,9,9) = 70
//
//x, 90 - x 20<=x<=70, 22 44 66
//11 | 90 - 2x, x = 34, 23
//
//  s              11 | 2x - 90, x = 56,67,78
//
//                                    x = 23,34,45,56,67
//
//                                        40561817703823564929

int ans = 0;

void dfs(int index, int cur_sum, vector<int> cnt, int sum)
{
//    cout << index << " " << cur_sum << " " << sum << endl;
    if(cur_sum > sum) return ;
    if(index == 10)
    {
        if(cur_sum == sum) ans++;
        else return ;
    }
    int s = index == 0 ? 1 : 0;

    for(int i = s; i < 6; i++)
    {
        if(cnt[i] > 0)
        {
            cnt[i] --;
            dfs(index + 1, cur_sum + i, cnt, sum);
            cnt[i] ++;
        }
    }

}

int main()
{
    vector<int> cnt(10, 2);
//    for(int i = 0; i < 10; i++) cout << cnt[i] << endl;
    dfs(0, 0, cnt, 23);
    cout << ans << endl;
    return 0;
}
