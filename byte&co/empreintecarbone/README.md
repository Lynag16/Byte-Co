$ curl "http://localhost:8084/api/carbon-footprint?km=3&transport=5"
{"data":[{"id":5,"name":"Voiture électrique","value":0.05940000000000001}]}

_____________________________________________________________________________________



curl -X POST http://localhost:8084/api/trajet/create -H "Content-Type: application/json" -d '{
    "idClient": 1,
    "dateTrajet": "2025-03-31T12:00:00",
    "pointDepart": "Paris",
    "pointArrivee": "Lyon",
    "moyenTransport": "Avion",
    "distance": 40.0
}'
{"idTrajet":20,"idClient":1,"dateTrajet":"2025-03-31T12:00:00.000+00:00","poin
tDepart":"Paris","pointArrivee":"Lyon","moyenTransport":"Avion","distance":40.
0,"empreinteCarbone":10.328}

