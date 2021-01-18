CREATE CONSTRAINT unique_project_name ON (p:Project) ASSERT p.name IS UNIQUE;
CREATE CONSTRAINT unique_certificate_name ON (c:Certificate) ASSERT c.name IS UNIQUE;
CREATE CONSTRAINT unique_company_name ON (c:Company) ASSERT c.name IS UNIQUE;