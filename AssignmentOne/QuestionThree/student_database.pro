stud_record(1, "Tebogo Molokomme", male, 12, [soccer, squash]).
stud_record(2, "Elsi Ledwaba", female, 10, [netball, aerobics, fencing, squash]).
stud_record(3, "Sam White", male, 11, [hockey, cricket]).
stud_record(4, "Jack Bloom", male, 10, [soccer, athletics]).
stud_record(5, "Lesley Manale", female, 12, [rugby, athletics, hockey]).
stud_record(6, "Ben Sefako", male, 11, [cricket, soccer, hockey]).

find_name([], S,N).
find_name([H|T], S , Nme) :-
	find_name(T, S, Nme),
	S = H,
	write(Nme),
	nl.

which_gender(N) :- 
	stud_record(ID, Nme, Gen, Grd, [H | T]), N = Nme, write(Gen).
which_grade(N) :-
	stud_record(ID, Nme, Gen, Grd, [H | T]), N = Nme, write(Grd).
which_sports(N) :-
	stud_record(ID, Nme, Gen, Grd, [H | T]), N = Nme, write([H]), write(T), nl.
sport_participants(S) :-
	stud_record(ID, Nme, Gen, Grd, [H | T]), (find_name(T, S, Nme); S = H, write(Nme), nl), fail.

