breed(beagle).
breed(labrador).
breed(dalmation).
breed(boxer).
breed(german_shephard).
breed(basset).

counts(B, C) :-
	breed(X).
	%C is count(), I) + 1.
counts(N, C, T) :-
	Z is C,
	breed(X),
	T is Z + 1,
	fail.

count([], C, T) :-
	T is 0.
count([H|Tail], C, T) :-
	Z is C,
	count(Tail, C, S),
	T is S + 1.
	
no_of_breeds(N) :-
	C is 0,
	BList = [beagle, labrador, dalmation, german_shephard, basset],
	count([H|BList],C, N).
		%C is count(breed(X), I)) + 1.