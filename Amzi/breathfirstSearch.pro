% initial_state([n, n, n, n]): All are initially on the north side.
% goal([s, s, s, s]): All are on the south side.
initial_state([n, n, n, n]).
goal([s, s, s, s]).

cross(n,s).
cross(s,n).

move([F, W, G, C], [F1, W, G, C]) :- cross(F, F1). % farmer crosses alone
move([F, F, G, C], [F1, F1, G, C]) :- cross(F, F1). % farmer takes wolf across
move([F, W, F, C], [F1, W, F1, C]) :- cross(F, F1). % farmer takes goat across
move([F, W, G, F], [F1, W, G, F1]) :- cross(F, F1). % farmer takes cabbage across

% safe([F, W, G, C]):
% A state is safe if the goat is with the farmer, or if it is alone.
safe([F, W, F, C]) :- !.
safe([F, F, G, F]).

% transition(S, S1): A move from state S to S1 is allowed if S1 can be
% reached from S in one river crossing, and S1 is a safe state.
transition(S, S1) :-
 	move(S, S1),
 	safe(S1).

 
% bfs(Solution):
% Performs a breadth-first search on the state space, producing
%  an answer path in Solution.

bfs(Solution) :-
 	initial_state(Start),
 	breadthfirst([[Start]], Solution).

breadthfirst([[Node | Path] | _], [Node | Path]) :-
 	goal(Node).

breadthfirst([Path | Paths], Solution) :-
 	extend(Path, NewPaths),
	conc(Paths, NewPaths, Paths1),
	breadthfirst(Paths1, Solution).

extend([Node | Path], NewPaths) :-
 	bagof([NewNode, Node | Path], (transition(Node, NewNode), 
 		not(member2(NewNode, [Node | Path]))), NewPaths), !.
 	extend(Path, []).

% See Bratko, page 64, for an explanation

member2(X, [X | Tail]).
member2(X, [Head | Tail]) :-
 	member2(X, Tail).


% See Bratko, page 65, for an explanation
conc([], L, L).
conc([X | L1], L2, [X | L3]) :-
 	conc(L1, L2, L3).