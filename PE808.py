import sympy

def isPerfectSquareNumber(x):
	y = x ** 0.5
	# print(x, y)
	return y.is_integer() and sympy.ntheory.isprime(int(y))


s = 0
c = 0
p = 2
while True:
	x = sympy.nextprime(p)
	y = x ** 2
	z = (int)(str(y)[::-1])
	if y != z and isPerfectSquareNumber(z):
		print(x, y, z)
		c += 1
		s += y
		if (c == 50): break
	p = x
		
print(s)
