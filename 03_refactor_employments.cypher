// Change name of relationship to current employment  
MATCH (e:Employee)-[rel:HAS_EMPLOYMENT]->(emp:Employment)
WHERE NOT EXISTS(emp.end_date)
MERGE (e)-[:CURRENT_EMPLOYMENT]->(emp)
DELETE rel;

// Change name of relationship to first employment
MATCH (e:Employee)-[rel:HAS_EMPLOYMENT]->(emp:Employment)
WITH e, rel, emp 	// order employments by start_date
	ORDER BY date(emp.start_date)
WITH e, collect(rel) AS relationships, collect(emp) AS employments	// collect
WITH e, relationships[0] AS rel_to_delete, employments[0] AS first_employment // first employment and first relationship
MERGE (e)-[:FIRST_EMPLOYMENT]->(first_employment)
DELETE rel_to_delete;

// connect employments with NEXT_EMPLOYMENT relationship
MATCH (e:Employee)-->(employment:Employment)
WITH e, employment		// order employments by start_date
	ORDER BY date(employment.start_date)
WITH e, collect(employment) AS allEmployments 	// collect
WITH e, allEmployments, range(0, size(allEmployments)) as indexes		// generate indexes
UNWIND indexes AS i
WITH allEmployments[i] as currentNode, allEmployments[i+1] AS nextNode	// get current node and next node
	WHERE i + 1 < size(allEmployments)
MERGE (currentNode)-[:NEXT_EMPLOYMENT]->(nextNode);

// delete HAS_EMPLOYMENT relationships
MATCH (:Employee)-[rel:HAS_EMPLOYMENT]->(:Employment)
DELETE rel;