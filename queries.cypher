
// Get employees with all employments
MATCH (e:Employee)-[:FIRST_EMPLOYMENT]->(firstemp:Employment)-[:NEXT_EMPLOYMENT]->(emp:Employment)-[:NEXT_EMPLOYMENT*]->(lastEmp:Employment)
RETURN e, firstemp, emp, lastEmp;