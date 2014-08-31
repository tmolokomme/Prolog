
split([],X,Y).

split([H|T], P, N) :-
	H >= 0, P = [X | H], split(T, P, N);
	N = [N | H], split(T, P, N). 