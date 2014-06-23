
% example, if L1 = [a, b, c] and L2 = [1, 2], then L = [a, 1, b, 2, c]. 

interleave([], [], L).
interleave([], [H2 | L2], L) :-
	write(','),
	write(H2),
	interleave([], L2, K).
	
interleave([H1 | L1], [], L) :-
	write(','),
	write(H1),
	interleave(L1, [], K).
		
interleave([H1 | L1], [H2 | L2], L) :-
	write(','),
	write(H1),
	write(','),
	write(H2),
	interleave(L1, L2, K).
	%R is L.
	%write("]").
interleaving([H1 | L1], [H2 | L2], L) :- 
	%H1 not blank,
	%H2 not blank,
	write('['),
	write(H1),
	write(','),
	write(H2),
	interleave(L1, L2, K),
	%R is L.
	write(']').
