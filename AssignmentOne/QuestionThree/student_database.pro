stud_record(1, "Tebogo Molokomme", male, 12, [soccer, squash]).
stud_record(2, "Elsi Ledwaba", female, 10, [netball, aerobics, fencing, squash]).
stud_record(3, "Sam White", male, 11, [hockey, cricket]).
stud_record(4, "Jack Bloom", male, 10, [soccer, athletics]).
stud_record(5, "Lesley Manale", female, 12, [rugby, athletics, hockey]).
stud_record(6, "Ben Sefako", male, 11, [cricket]).

who(X, N, FirstSport) :-
	X = FirstSport, write(N).
	
who(X, N, [Head | Tail]) :- 
	
	Ve = Head,
	who(X, N, Ve), who(X, N, Tail), write(N).

which_gender(Name) :- 
	stud_record(ID, StudentName, Gender, Grade, [FirstSport | Sports]), N = StudentName, write(Gender).
which_grade(Name) :-
	stud_record(ID, StudentName, Gender, Grade, [FirstSport | Sports]), N = StudentName, write(Grade).
which_sports(Name) :-
	stud_record(ID, StudentName, Gender, Grade, [FirstSport | Sports]), N = StudentName, write(Sports).
sport_participants(S) :-
	stud_record(ID, StudentName, Gender, Grade, [FirstSport | Sports]), memeber(S,StudentName, Sports) ; S = FirstSport, write(StudentName).
