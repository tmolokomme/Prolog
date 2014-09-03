
split([],X,Y).

eq(T, H, A, B) :-
	T == '[]', B = H, !;
	B = [H|A].
	
split([H|T], Px, Ny) :-
	H >= 0, split(T, P, N), eq(T, H, P, Pk), Px = Pk,!;
	split(T, P, N), eq(T, H, N, Nk), Ny = Nk. 