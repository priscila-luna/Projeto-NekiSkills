/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable react-hooks/rules-of-hooks */
import UserSkillCard from "./UserSkillCard";
import { useContext, useEffect } from "react";
import { userSkillResponse } from "@/types/skillTypes";
import { DataContext, DataContextType } from "@/context/DataContext";
import { Separator } from "@/components/ui/separator";
import { Card, CardContent } from "./ui/card";
import { FileQuestion } from "lucide-react";
export default function UserSkillList() {
  const { fetchUserSkills, userSkills } = useContext(
    DataContext
  ) as DataContextType;

  useEffect(() => {
    fetchUserSkills();
  }, []);

  return (
    <div className="flex flex-col gap-4">
      <div className="flex flex-col gap-1">
        <h1 className="text-2xl font-medium"> Minhas Skills</h1>
        <span className="font-thin text-muted-foreground">
          Clique em uma skill para editá-la
        </span>
      </div>
      <Separator />

      {userSkills && userSkills?.length > 0 ? (
        <ul className="flex flex-row gap-x-8 overflow-x-scroll snap-x scrollbar-hide sm:scrollbar-default">
          {userSkills.map((skill: userSkillResponse) => (
            <li className="snap-center" key={skill.userSkills.id}>
              <UserSkillCard skill={skill} />
            </li>
          ))}
        </ul>
      ) : (
        <Card className="w-full h-max py-8">
          <CardContent
            className="h-full 
          flex 
          flex-col 
          justify-center 
          items-center 
          p-0 
          text-muted-foreground
          gap-y-2
          opacity-70
          "
          >
            <FileQuestion className="w-12 h-12" />
            <div className="flex flex-col items-center gap-0 text-center">
              <h1 className="font-semibold">
                Opa! parece que você não adicionou nenhuma skill.
              </h1>
              <span className="font-extralight p-0">
                Utilize a barra de pesquisa para encontrar suas skills
              </span>
            </div>
          </CardContent>
        </Card>
      )}
    </div>
  );
}
