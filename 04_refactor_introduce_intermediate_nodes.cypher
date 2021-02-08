// Extract "Assignment" nodes from "ASSIGNED_PROJECT" relationship
MATCH (e:Employment)-[rel:ASSIGNED_PROJECT]->(p:Project)
CALL apoc.refactor.extractNode(rel, ['Assignment'], 'ASSIGNED_TO', 'CONTAINS')
YIELD input, output
RETURN input, output;

// Extract "Examination" nodes from "PASSED_EXAM" relationship
MATCH (e:Employee)-[rel:PASSED_EXAM]->(c:Certificate)
CALL apoc.refactor.extractNode(rel, ['Examination'], 'HANDS_OUT', 'PASSED')
YIELD input, output
RETURN input, output;