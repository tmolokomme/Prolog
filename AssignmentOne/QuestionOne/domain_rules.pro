named(beagle, oscar).
named(labrador, Name) :- 
	Name = zappa ; Name = penza.
named(dalmation, domino).
named(boxer, daisy).

beagle(oscar).
labrador(zappa).


owns(waldo, oscar).
owns(sjanie, zappa).
owns(peter, domino).
owns(john, daisy).
owns(ander, penza).

owns(waldo, X) :- named(X, oscar).
owns(sjanie, labrador) :- named(labrador, zappa).
owns(peter, dalmation) :- named(dalmation, domino).
owns(john, boxer) :- named(boxer, daisy).
owns(ander, labrador) :- named(labrador, penza).

%owns(waldo, beagle).
%owns(sjanie, labrador).
%owns(peter, dalmation).
%owns(john, boxer).
%owns(ander, labrador).

type(oscar, beagle).
type(zappa, labrador).
type(domino, dalmation).
type(daisy, boxer).
type(penka, labrador).

dogsize(mediumdog).
dogsize(largedog).

mediumdog(beagle).
mediumdog(beagle) :- hunting(beagle).
%mediumdog(bassets).
largedog(labrador).
largedog(labrador) :- guide(labrador).

use(X, hunting) :- mediumdog(X); type(X, Y), mediumdog(Y).
use(X, guide) :- largedog(X); type(X, Y), largedog(Y).
%use(DogType, hunting) :- mediumdog(Dogtype).
use(bassets, hunting).
use(mediumdog, hunting).
use(largedog, guide).
%use(DogType, guide) :- largedog(DogType).
use(gshep, watch).