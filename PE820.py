import math

start = time()
n = 10000000
s = 0
for i in range(2, n + 1):
	b = pow(10, n-1, i) / i;
	s += math.floor(b *10)
print(s)
print("time = "+str(time()-start))
