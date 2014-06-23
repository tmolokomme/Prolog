% rate is 5 on weekend.
% rate is 7 on weekday during nighttime.
% rate is 9 on weekday during daytime.

rate(Day, Hour, 5) :-
	weekend(Day).
rate(Day, Hour, 7) :-
	weekday(Day), daytime(Hour).
rate(Day, Hour, 5) :-
	weekday(Day), nighttime(Hour).

weekend(saterday).
weekend(sunday).

weekday(monday).
weekday(tuesday).
weekday(wednesday).
weekday(thurday).
weekday(friday).

nighttime(Hour) :-
	Hour > 19.
nighttime(Hour) :-
	Hour < 8.

daytime(Hour) :-
	Hour >= 8,
	Hour =< 19.
	
