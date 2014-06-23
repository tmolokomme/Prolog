% FE - UI interface for phone pricing application

% Use repeat/fail loop to price each call
% Calculate the rate based on a Day and Hour and 
% multiply by the duration

main :-
	service_rules(ID, Day, Hour, Duration),
	rate(Day, Hour, Rate),
	Price is Rate * Duration,
	write(ID), tab(2),
	write(Day), tab(2),
	write(Hour), tab(2),
	write(Price), nl,
	fail.
main.