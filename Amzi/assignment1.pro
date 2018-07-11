expo(B, 0, 1).
expo(0, E, 0).
expo(B, E, R) :-
	B > 0, 
	E > 0,
	E1 is E-1,
	expo(B, E1, R1),
	R is B*R1.
	
sum([], 0, 0).
sum([H | T], I, S) :-
	sum(T, I1, S1),
	I is I1 + 1,
	S is H + S1.

average([], 0).
average([H|T], X) :-
	sum(T, C1, X1),
	C is 1 + C1,
	X is (H + X1)/C .
	
greater(X, R) :-
	X > 5, R = true, !;
	R = false.
	
interleave([], L, L).
interleave(L, [], L).
interleave([H1|T1], [H2|T2], [H1,H2|L]) :-
	interleave(T1, T2, L).
	

ints([], _, []).
ints([H1|T1], L2, [H1|L]) :-
	member(H1, L2),!, ints(T1, L2, L).
ints([H1|T1], L2, L) :-	
	ints(T1, L2, L).