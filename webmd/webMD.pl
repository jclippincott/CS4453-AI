:- dynamic(known/1).

whatsicknessis(X,Y):-
    sickness(X,Y);
    not(known(fever_strength(X,Y))),ask_fever(X,A),sickness(X,Y);
    not(known(headache_strength(X,Y))),ask_headache(X,B),sickness(X,Y);
    not(known(pains_strength(X,Y))),ask_pains(X,C),sickness(X,Y);
    not(known(fatigue_strength(X,Y))),ask_fatigue(X,D),sickness(X,Y);
    not(known(exhaustion_strength(X,Y))),ask_exhaustion(X,E),sickness(X,Y);
    not(known(stuffyNose_strength(X,Y))),ask_stuffyNose(X,F),sickness(X,Y);
    not(known(sneezing_strength(X,Y))),ask_sneezing(X,G),sickness(X,Y);
    not(known(soreThroat_strength(X,Y))),ask_soreThroat(X,H),sickness(X,Y);
    not(known(cough_strength(X,Y))),ask_cough(X,I),sickness(X,Y);
    not(known(wateryEye_strength(X,Y))),ask_wateryEye(X,J),sickness(X,Y).

sickness(X,flu):-
    fever_strength(X,high),
    headache_strength(X,prominent),
    pains_strength(X,severe),
    fatigue_strength(X,severe),
    exhaustion_strength(X,prominent),
    stuffyNose_strength(X,sometimes),
    sneezing_strength(X,sometimes),
    soreThroat_strength(X,sometimes),
    cough_strength(X,severe),
    wateryEye_strength(X,none).
sickness(X,cold):-
    fever_strength(X,rare),
    headache_strength(X,rare),
    pains_strength(X,slight),
    fatigue_strength(X,mild),
    exhaustion_strength(X,none),
    stuffyNose_strength(X,common),
    sneezing_strength(X,usual),
    soreThroat_strength(X,common),
    cough_strength(X,mild),
    wateryEye_strength(X,rare).
sickness(X,allergy):-
    fever_strength(X,never),
    headache_strength(X,never),
    pains_strength(X,never),
    fatigue_strength(X,mild),
    exhaustion_strength(X,none),
    stuffyNose_strength(X,common),
    sneezing_strength(X,sometimes),
    soreThroat_strength(X,sometimes),
    cough_strength(X,severe),
    wateryEye_strength(X,common).
sickness(X,ebola):-
    fever_strength(X,severe),
    headache_strength(X,severe),
    pains_strength(X,severe),
    fatigue_strength(X,severe),
    exhaustion_strength(X,severe),
    stuffyNose_strength(X,none),
    sneezing_strength(X,none),
    soreThroat_strength(X,none),
    cough_strength(X,none),
    wateryEye_strength(X,none).
sickness(X,bubonicPlague):-
    fever_strength(X,high),
    headache_strength(X,high),
    pains_strength(X,high),
    fatigue_strength(X,high),
    exhaustion_strength(X,high),
    stuffyNose_strength(X,none),
    sneezing_strength(X,none),
    soreThroat_strength(X,none),
    cough_strength(X,none),
    wateryEye_strength(X,none).

fever_strength(X,Y):- known(fever_strength(X,Y)).
headache_strength(X,Y):- known(headache_strength(X,Y)).
pains_strength(X,Y):- known(pains_strength(X,Y)).
fatigue_strength(X,Y):- known(fatigue_strength(X,Y)).
exhaustion_strength(X,Y):- known(exhaustion_strength(X,Y)).
stuffyNose_strength(X,Y):- known(stuffyNose_strength(X,Y)).
sneezing_strength(X,Y):- known(sneezing_strength(X,Y)).
soreThroat_strength(X,Y):- known(soreThroat_strength(X,Y)).
cough_strength(X,Y):- known(cough_strength(X,Y)).
wateryEye_strength(X,Y):- known(wateryEye_strength(X,Y)).

ask_fever(X,Y):-
    write('Enter fever symptoms (severe, high, rare, never): '),
    read(Y),
    asserta(known(fever_strength(X,Y))).
ask_headache(X,Y):-
    write('Enter headache symptoms (severe, high, prominent, rare, never): '),
    read(Y),
    asserta(known(headache_strength(X,Y))).
ask_pains(X,Y):-
    write('Enter pain symptoms (severe, high, slight, none): '),
    read(Y),
    asserta(known(pains_strength(X,Y))).
ask_fatigue(X,Y):-
    write('Enter fatigue symptoms (severe, high, mild, none): '),
    read(Y),
    asserta(known(fatigue_strength(X,Y))).
ask_exhaustion(X,Y):-
    write('Enter exhaustion symptoms (severe, high, prominent, none): '),
    read(Y),
    asserta(known(exhaustion_strength(X,Y))).
ask_stuffyNose(X,Y):-
    write('Enter stuffy nose frequency (common, sometimes, none): '),
    read(Y),
    asserta(known(stuffyNose_strength(X,Y))).
ask_sneezing(X,Y):-
    write('Enter sneezing frequency (usual, sometimes, none): '),
    read(Y),
    asserta(known(sneezing_strength(X,Y))).
ask_soreThroat(X,Y):-
    write('Enter sore throat frequency (common, sometimes, none): '),
    read(Y),
    asserta(known(soreThroat_strength(X,Y))).
ask_cough(X,Y):-
    write('Enter cough symptoms (severe, sometimes, mild, none): '),
    read(Y),
    asserta(known(cough_strength(X,Y))).
ask_wateryEye(X,Y):-
    write('Enter watery eye frequency (rare, common, none): '),
    read(Y),
    asserta(known(wateryEye_strength(X,Y))).


