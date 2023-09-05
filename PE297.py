N = (10 ** 17) - 1
f = [1, 2]
mp = {0:0}
while True:
	f.append(f[-1] + f[-2])
	if f[-1] > N:
		break

def dfs(n: int):
	if n in mp.keys():
		return mp[n]
	p = 0
	while p + 1 < len(f) and f[p + 1] <= n:
		p += 1
	ans = dfs(f[p] - 1) + dfs(n - f[p]) + n - f[p] + 1
	mp[n] = ans
	return ans
print(dfs(N))
