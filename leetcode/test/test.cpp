#include <iostream>

class Entity
{
public:
    virtual std::string GetName() { return "Entity"; }
};

class Player : public Entity
{
private:
    std::string m_Name;

public:
    Player(const std::string& name)
        : m_Name(name) {}

    std::string GetName() override { return m_Name; }
};

int main()
{
    Entity* entity = new Entity();
    Entity* player = new Player("hjn");
    std::cout << entity->GetName() << std::endl;
    std::cout << player->GetName() << std::endl;
}