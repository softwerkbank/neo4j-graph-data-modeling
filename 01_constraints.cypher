CALL apoc.schema.assert({}, {});
CREATE CONSTRAINT unique_project_name ON (p:Project) ASSERT p.name IS UNIQUE;
CREATE CONSTRAINT unique_certificate_name ON (c:Certificate) ASSERT c.name IS UNIQUE;
CREATE CONSTRAINT unique_technology_name ON (t:Technology) ASSERT t.name IS UNIQUE;
CREATE CONSTRAINT unique_methodology_name ON (m:Methodology) ASSERT m.name IS UNIQUE;
CREATE CONSTRAINT unique_company_name ON (c:Company) ASSERT c.name IS UNIQUE;