
% exp(base, Exponent, Result)

exp(X, 0, R) :-
	R is 1.
exp(0, Y, R) :-
	R is 0.
exp(X, Y, R) :-
	X > 0, 
	Y > 0,
	Z is Y - 1,
	exp(X, Z, K),
	R is X * K.