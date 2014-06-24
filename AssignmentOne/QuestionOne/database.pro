breed(beagle).
breed(labrador).
breed(dalmation).
breed(boxer).
breed(german_shephard).
breed(basset).

named(beagle, oscar).
named(labrador, zappa).
named(dalmanation, domino).
named(boxer, daisy).
named(labrador, penka).

name_(X , N) :- breed(X) , named(X, N).

owner(waldo).
owner(sjanie).
owner(peter).
owner(john).
owner(andre).

own(waldo, oscar).
own(sjanie, zappa).
own(peter, domino).
own(john, daisy).
own(andre,penka). 

owned_breed(B, O) :- named(B, N), own(O, N).
owners_dog_use(O, U) :- own(O, N), named(B, N), use(B, U).
owns(O, B) :- owned_breed(B, O).
owns_guide_dog(O) :- own(O, N), named(B, N), use(B, guide).

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
	
size(medium, beagle).
size(large, labrador).

use(beagle, hunt).
use(basset, hunt).
use(labrador, guide).
use(germanshepard, watch).

