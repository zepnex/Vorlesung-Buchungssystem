# How to use

## Platzhalter
<Datum> date from 1 to 365 <br>
<Rolle> security, lecturer, student <br>
<3G-Nachweis> tested, recovered, vaccinated <br>
<Gebäudename> name of the hal l<br>
<Verordnung> 3G or 2G <br>
## set date
set-date <<Datum>> 

## Add Person to system
add-person <Rolle>;<Vorname>;<Nachname>

## add Covid-Certificate
add-certificate <Personen-Id>;<3G-Nachweis>;<Datum>

## print person
print-person <Personen-Id>

## print people
print-people <Rolle>

## add event
add-event <Personen-Id>;<Gebäudename>;<Kapazität>;<Verordnung>;<Datum>

## increase security
increase-security <Event-Id>;<Personen-Id>

## book spot
book-spot <Event-Id>;<Personen-Id>

## report case
report-case <PersonId>

## quit
quit
