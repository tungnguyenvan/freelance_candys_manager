import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {


    public List<CandyModel> candysreadFile(String url) throws Exception{
        List<CandyModel> candys = new ArrayList<>();
        File file = new File(url);
        FileReader fr = new FileReader(file);

        BufferedReader br = new BufferedReader(fr);
        int coutLine = 1;
        String line;
        CandyModel candyModel = null;
        while ((line = br.readLine()) != null){
            switch (coutLine){
                case 1 :
                    candyModel = new CandyModel();
                    candyModel.setId(Integer.parseInt(line));
                    break;
                case 2:
                    candyModel.setName(line);
                    break;
                case 3:
                    candyModel.setTotal(Integer.parseInt(line));
                    break;
                case 4:
                    candyModel.setPrice(Integer.parseInt(line));
                    candys.add(candyModel);
                    candyModel = null;
                    coutLine = 0;
                    break;
            }
            coutLine++;
        }

        br.close();
        fr.close();
        return candys;
    }

    public boolean saveFile(List<CandyModel> cadys, String url){
        File file = new File(url);
        String content = "";
        for (int i =0; i < cadys.size(); i++){
            if (i == cadys.size() -1){
                content += cadys.get(i).getId() +"\n" + cadys.get(i).getName()
                        + "\n" + cadys.get(i).getTotal() + "\n" + cadys.get(i).getPrice();
            }else {
                content += cadys.get(i).getId() +"\n" + cadys.get(i).getName()
                        + "\n" + cadys.get(i).getTotal() + "\n" + cadys.get(i).getPrice() + "\n";
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream(file);
            if (!file.exists()){
                file.createNewFile();
            }
            byte[] contentInByte = content.getBytes();
            fos.write(contentInByte);
            fos.flush();
            fos.close();

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
