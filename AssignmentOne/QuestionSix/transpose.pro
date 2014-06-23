
% , if L1 = [a, b, c] and L2 = [1, 2, 3], then
%	L = [(a, 1), (b, 2), (c, 3)]

transpose([], [], L) :-
	L = ''.
transpose([], [H2 | L2], L) :-
	write(', ('),
	write(H2),
	write(')'),
	X3 = (H2),
	transpose([], L2, K),
	L = (X3 , K).
	
transpose([H1 | L1], [], L) :-
	write(', ('),
	write(H1),
	write(')'),
	X3 = (H1), 
	transpose(L1, [], K),
	L = (X3 , K).
		
transpose([H1 | L1], [H2 | L2], L) :-
	write(', ('),
	write(H1),
	write(','),
	write(H2),
	write(')'),
	X1 = (H1, H2),
	transpose(L1, L2, K),
	L = (X1 , K).
	%R is L.
	%write("]").
transposing([H1 | L1], [H2 | L2], L) :- 
	%H1 not blank,
	%H2 not blank,
	write('[('),
	write(H1),
	write(','),
	write(H2),
	write(')'),
	X = (H1 , H2),
	transpose(L1, L2, K),
	%R is L.
	L = [X , K],
	write(']').
