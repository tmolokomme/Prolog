
%inner_prod(L1,L2,X) 
% If we have two vectors [a1, a2,...,  an] and [b1, b2,..., bn] then
%	X = a1×b1 + a2×b2 +... + an×bn.

inner_prod([], [], L) :-
	L is 0.
inner_prod([], [H2 | L2], L) :-
	write(' + '),
	write(H2),
	write(''),
	X3 is H2*1,
	inner_prod([], L2, K),
	L is X3 + K.
	
inner_prod([H1 | L1], [], L) :-
	write(' + '),
	write(H1),
	write(''),
	X3 is H1*1,
	inner_prod(L1, [], K),
	L is X3 + K.
		
inner_prod([H1 | L1], [H2 | L2], L) :-
	write(' + '),
	write(H1),
	write('*'),
	write(H2),
	write(''),
	X2 is H1*H2,
	inner_prod(L1, L2, K),
	L is X2 + K.
	%R is L.
	%write("]").
	
inner_product([H1 | L1], [H2 | L2], X) :- 
	%H1 not blank,
	%H2 not blank,
	write('X = '),
	write(H1),
	write('*'),
	write(H2),
	write(''),
	X1 is H1*H2,
	inner_prod(L1, L2, K),
	%R is L.
	write('.'),
	X is X1 + K.
