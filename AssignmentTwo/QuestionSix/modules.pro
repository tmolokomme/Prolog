student(doe,john,hons,[cos4807,cos4840,cos4851]).
student(soap,joe,degree,[cos4807,cos4840]).


modules(Name, List) :-
	student(Name, _ , _ , List). 