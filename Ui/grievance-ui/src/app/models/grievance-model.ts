import { CleanGreenModel } from "./clean-green-model";
import { HealthModel } from "./health-model";


export class GrievanceModel {

    grievanceType!:String;
  
    grievanceSubType!:String;

    healthModel:HealthModel =new HealthModel();

    cleanAndGreenModel:CleanGreenModel=new CleanGreenModel();

    complaintDescription!:String;
}
