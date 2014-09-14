%%%%%% 4.2 %%%%%%%%
append([],X,X).

append([H|T1],X,[H|T2]) :-
  append(T1,X,T2).

add_element(X, [] , X).

add_element(X, [H|T], L1) :-
	append([X], [H|T], L1). 
	
%%%%%% 4.2 %%%%%%%%%
difference([], [], L).
difference([], X, X).
difference(L, [], L).
difference([], [], L).
%difference([H|T], [H2|T2], L) :-
	
%%%%%% 4.2 %%%%%%%%%	

equa(X, X, C) :- 
	C is 1.
equa(X, Y, C) :- 
	C is 0.

count(X, [], K) :-
	K is 0.
count(X, [H|T], K) :-
	equa(X, H, C),
	count(X, T, D),
	K is D + C.
		
occurrences(X, [], N) :-
	V is 0,
	N =:= V.

occurrences(X, [H|T], N) :-	
		count(X, [H|T], V),
		N =:= V,!;
		fail.

%%%%%% 4.3 occurs(N, L, X) %%%%%%%%%
% HOLDS iff X is an element occuring in position N of the list L
position(X, [], I, B).

eq(X, X) :- true.
%eq(X, Y) :- not(true).

position(X, [H|T], I, B) :-
	eq(X, H), !, I is B,!;
	S is B + 1,
	position(X, T, Ix, S).

occurs(N, [], X) :-
	fail.

occurs(N, [H|T], X) :-	
		S is 1,
		position(X, [H|T], P, S),
		N =:= P,!;
		fail.
