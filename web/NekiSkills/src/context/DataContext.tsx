import { api } from "@/services/axios";
import { userSkillRequest, userSkillResponse } from "@/types/skillTypes";
import { ReactNode, createContext, useContext, useState } from "react";
import { AuthContext } from "./AuthContext";
import { AuthContextType } from "@/types/authTypes";
import { compararItemsUserSkill } from "@/services/utils";

type DataContextProps = {
  children: ReactNode;
};

export type DataContextType = {
  fetchUserSkills: () => void;
  userSkills: userSkillResponse[] | null;
  linkUserSkill: (data: userSkillRequest) => Promise<void>;
  unlinkUserSkill: (skillId: string) => Promise<void>;
  updateUserSkill: (data: userSkillRequest) => Promise<void>;
};

export const DataContext = createContext<DataContextType | null>(null);

export const DataProvider = ({ children }: DataContextProps) => {
  const [userSkills, setUserSkills] = useState<userSkillResponse[] | null>(
    null
  );
  const { getUserId } = useContext(AuthContext) as AuthContextType;

  async function fetchUserSkills() {
    api
      .get(`/user-skills/user/${getUserId()}`)
      .then((res) => {
        const UserSkillResponseList: userSkillResponse[] = res.data;
        setUserSkills(UserSkillResponseList.sort(compararItemsUserSkill));
      })
      .catch((err) => console.error("Error fetching data:", err));
  }

  async function linkUserSkill(data: userSkillRequest) {
    try {
      await api.post("/user-skills", data);
      await fetchUserSkills();
    } catch (error) {
      console.error("Error linking user skill:", error);
    }
  }

  async function unlinkUserSkill(skillId: string) {
    try {
      await api.delete(`/user-skills/${getUserId()}/${skillId}`);
      await fetchUserSkills();
    } catch (error) {
      console.error("Error unlinking user skill:", error);
    }
  }

  async function updateUserSkill(data: userSkillRequest) {
    try {
      await api.put("/user-skills", data);
      await fetchUserSkills();
    } catch (error) {
      console.error("Error updating user skill:", error);
    }
  }

  return (
    <DataContext.Provider
      value={{
        fetchUserSkills,
        userSkills,
        linkUserSkill,
        unlinkUserSkill,
        updateUserSkill,
      }}
    >
      {children}
    </DataContext.Provider>
  );
};
