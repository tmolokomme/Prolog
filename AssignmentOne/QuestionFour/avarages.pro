
% Write a predicate average with two arguments: the first 
% argument is a list of integers or reals, and the predicate must 
% match the second argument with the average of the numbers in the list.

sumxss([Head|Tail], K, C) :-
	Head < 0,
	K is 0,
	C is C + 1.

sum([], X, C) :-
	X is 0,
	C is 0.
sum([Head|Tail], X, C) :- 
	Head >= 0,
	B = Head,
	sum(Tail, K, Cn),
	X is B + K,
	C is Cn + 1.
	
average([Head | Tail], M) :-
	A is Head,
	A >= 0,
	sum(Tail, N, I),
	C is I + 1,
	M is (A + N)/C.