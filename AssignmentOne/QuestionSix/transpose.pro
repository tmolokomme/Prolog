
% , if L1 = [a, b, c] and L2 = [1, 2, 3], then
%	L = [(a, 1), (b, 2), (c, 3)]

transpose([], [], L).
transpose([], [H2 | L2], L) :-
	write(', ('),
	write(H2),
	write(')'),
	transpose([], L2, K).
	
transpose([H1 | L1], [], L) :-
	write(', ('),
	write(H1),
	write(')'),
	transpose(L1, [], K).
		
transpose([H1 | L1], [H2 | L2], L) :-
	write(', ('),
	write(H1),
	write(','),
	write(H2),
	write(')'),
	transpose(L1, L2, K).
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
	transpose(L1, L2, K),
	%R is L.
	write(']').
