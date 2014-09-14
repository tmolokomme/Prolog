
count_vars([], N) :-
			N is 0.		

init(H, V) :-
	integer(H), 
	V is 1,!;
	V is 0.

count_vars([H|T], X) :-
			init(H, V),
			count_vars(T, K),
			X is V + K.
			%Tail recursive (need to fixed)
			%X is V + count_vars(T, K).