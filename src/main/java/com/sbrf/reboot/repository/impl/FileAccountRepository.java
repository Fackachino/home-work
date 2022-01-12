package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.ClientNotFoundException;
import com.sbrf.reboot.repository.AccountRepository;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileAccountRepository implements AccountRepository {
    private String pathToAccountsFile;

    @Override
    public Set<Long> getAllAccountsByClientId(long clientId) {
        List<String> notesList = getAllNotesFromFile();
        Map<Long, HashSet<Long>> idsNumberMap = getClientIdsAndTheirNumbers(notesList);
        if (!idsNumberMap.containsKey(clientId)) {
            throw new ClientNotFoundException("Client ID not found");
        }
        return idsNumberMap.get(clientId);
    }

    @Override
    public boolean addContractToClient(long clientId, long contractNumber) {
        return false;
    }

    public FileAccountRepository(String pathToAccountsFile) {
        this.pathToAccountsFile = pathToAccountsFile;
    }

    public List<String> getAllNotesFromFile() {
        List<String> allNotes = new ArrayList<>();
        Pattern notePattern = Pattern.compile("\\{\\s+\"clientId\":\\s[0-9]+,\\s+\"number\":\\s[0-9]+\\s+}");
        StringBuffer stringBuffer = new StringBuffer();
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(pathToAccountsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            int i = -1;
            while ((i = fileInputStream.read()) != -1) {
                stringBuffer.append((char) i);
            }
            fileInputStream.close();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }

        Matcher m = notePattern.matcher(stringBuffer);
        while (m.find()) {
            allNotes.add(m.group());
        }

        return allNotes;
    }

    public Map<Long, HashSet<Long>> getClientIdsAndTheirNumbers(List<String> allNotes) {
        Map<Long, HashSet<Long>> mapNotes = new HashMap<>();
        Pattern clientPattern = Pattern.compile("\"clientId\":\\s[0-9]+,");
        Pattern numberPattern = Pattern.compile("\"number\":\\s[0-9]+");

        for (String str : allNotes) {
            String[] resultStr;
            Matcher m1 = clientPattern.matcher(str);
            m1.find();
            resultStr = (m1.group().split(" "));
            String resultedStr = resultStr[1].substring(0, resultStr[1].length()-1);
            Long resultID = Long.parseLong(resultedStr);

            Matcher m2 = numberPattern.matcher(str);
            m2.find();
            resultStr = (m2.group().split(" "));
            Long resultNumber = Long.parseLong(resultStr[1]);

            if (mapNotes.containsKey(resultID)) {
                mapNotes.get(resultID).add(resultNumber);
            } else {
                mapNotes.put(resultID, new HashSet<Long>(Collections.singleton(resultNumber)));
            }
        }

        return mapNotes;
    }

    public void updateContractNumber(long clientID, long oldContractNumber, long newContractNumber){
        List<String> allNotes = new ArrayList<>();
        Pattern clientPattern = Pattern.compile("\"clientId\":\\s[0-9]+,");
        Pattern numberPattern = Pattern.compile("\"number\":\\s[0-9]+");
        StringBuffer stringBuffer = new StringBuffer();
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(pathToAccountsFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            int i = -1;
            while ((i = fileInputStream.read()) != -1) {
                stringBuffer.append((char) i);
            }
            fileInputStream.close();
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }

        Matcher clientM = clientPattern.matcher(stringBuffer);
        Matcher numberM = numberPattern.matcher(stringBuffer);

        while (clientM.find() && numberM.find()) {
            String strID = stringBuffer.substring(clientM.start()+12,clientM.end()-1);
            String strNumber = stringBuffer.substring(numberM.start()+10,numberM.end());
            if(Long.parseLong(strID) == clientID && Long.parseLong(strNumber) == oldContractNumber){
                stringBuffer.replace(numberM.start()+10, numberM.end(), Long.toString(newContractNumber));
            }
        }

        System.out.println(stringBuffer);
        BufferedWriter bwr = null;
        try {
            bwr = new BufferedWriter(new FileWriter(new File(pathToAccountsFile)));
            bwr.write(stringBuffer.toString());
            bwr.flush();
            bwr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}