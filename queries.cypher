
// Get employees with all employments
MATCH (e:Employee)-[:FIRST_EMPLOYMENT]->(firstemp:Employment)-[:NEXT_EMPLOYMENT]->(emp:Employment)-[:NEXT_EMPLOYMENT*]->(lastEmp:Employment)
RETURN e, firstemp, emp, lastEmp;

MATCH p1 = (e:Employee)-[:FIRST_EMPLOYMENT]->(:Employment)-[:NEXT_EMPLOYMENT]->(:Employment)-[:NEXT_EMPLOYMENT*]->(:Employment)
MATCH p2 = (e)-[:PASSED]->(:Examination)-[:HANDS_OUT]->(:Certificate)
MATCH p3 = (e)-[:PARTICIPATED_COURSE]->(:Course)-[:TRANSFERS_KNOWLEDGE]->()
WHERE e.loginname = 'jessle'
RETURN p1, p2, p3;