# classes/ 디렉토리 파일 목록

> 작성일: 2026-02-23
> 총 파일 수: **449개** (.class)

---

## 1. common — 공통 레이어 (50개)

### 루트 (10개)
| # | 파일명 | 설명 |
|---|--------|------|
| 1 | BlobView.class | Blob 뷰 |
| 2 | DBussinessManager.class | 비즈니스 매니저 |
| 3 | DCookieUtil.class | 쿠키 유틸 |
| 4 | DMessage.class | 메시지 처리 |
| 5 | DMultiActionController.class | 멀티액션 컨트롤러 |
| 6 | DMultiEgovAbstractMapper.class | 멀티 eGov 매퍼 |
| 7 | ExcelView.class | 엑셀 뷰 |
| 8 | FirebaseInitializer.class | FCM 푸시 초기화 |
| 9 | LoginCheckInterceptor.class | 로그인 체크 인터셉터 |
| 10 | LoginManager.class | 로그인 매니저 |

### controller/ (4개)
| # | 파일명 |
|---|--------|
| 1 | CommonController.class |
| 2 | ErrorController.class |
| 3 | GridController.class |
| 4 | LoginController.class |

### crontab/ (4개)
| # | 파일명 | 비고 |
|---|--------|------|
| 1 | PushBatchSender.class | 푸시 배치 발송 |
| 2 | PushBatchSender$1.class | 내부 익명 클래스 |
| 3 | PushBatchSender$BatchResult.class | 내부 클래스 |
| 4 | Scheduler.class | 스케줄러 |

### service/ (5개)
| # | 파일명 |
|---|--------|
| 1 | ChatService.class |
| 2 | CommonService.class |
| 3 | CommonUserService.class |
| 4 | GridService.class |
| 5 | SchedulerService.class |

### service/impl/ (10개)
| # | 파일명 |
|---|--------|
| 1 | ChatServiceDAO.class |
| 2 | ChatServiceImpl.class |
| 3 | CommonDAO.class |
| 4 | CommonServiceImpl.class |
| 5 | CommonUserDAO.class |
| 6 | CommonUserServiceImpl.class |
| 7 | GridDAO.class |
| 8 | GridServiceImpl.class |
| 9 | SchedulerDAO.class |
| 10 | SchedulerServiceImpl.class |

### util/ (17개)
| # | 파일명 | 설명 |
|---|--------|------|
| 1 | AddTag.class | 태그 추가 |
| 2 | CORSFilter.class | CORS 필터 |
| 3 | CookieUtil.class | 쿠키 유틸 |
| 4 | DateUtil.class | 날짜 유틸 |
| 5 | DrawImage.class | 이미지 생성 |
| 6 | EgovComExcepHndlr.class | eGov 예외 핸들러 |
| 7 | EgovComOthersExcepHndlr.class | eGov 기타 예외 핸들러 |
| 8 | EgovResourceCloseHelper.class | 리소스 종료 헬퍼 |
| 9 | EgovWebUtil.class | eGov 웹 유틸 |
| 10 | ExcelWriter.class | 엑셀 작성기 |
| 11 | FileUtil.class | 파일 유틸 |
| 12 | FileUtil$Graph.class | 내부 클래스 |
| 13 | ImagePaginationRenderer.class | 이미지 페이지네이션 |
| 14 | PaginationUtil.class | 페이지네이션 유틸 |
| 15 | PushMessageUtil.class | 푸시 메시지 유틸 |
| 16 | SimpleDateUtil.class | 간단 날짜 유틸 |
| 17 | StringUtil.class | 문자열 유틸 |

---

## 2. mhcapp — 모바일 앱 백엔드 (36개)

### cm (공통) — 4개
| 계층 | 파일명 |
|------|--------|
| controller | AppCmmnJobController.class |
| service | AppCmmnJobService.class |
| impl | AppCmmnJobDAO.class |
| impl | AppCmmnJobServiceImpl.class |

### mr (경영보고) — 4개
| 계층 | 파일명 |
|------|--------|
| controller | AppGeneralCnslController.class |
| service | AppGeneralCnslService.class |
| impl | AppGeneralCnslDAO.class |
| impl | AppGeneralCnslServiceImpl.class |

### sv (서비스) — 28개
| 계층 | 파일명 |
|------|--------|
| controller | AfterServeyController.class |
| controller | CommunityController.class |
| controller | ExceEvalController.class |
| controller | MonthlyReportController.class |
| controller | MyHealthMainDashController.class |
| controller | NutEvalController.class |
| controller | RankController.class |
| controller | ServeyController.class |
| service | AfterServeyService.class |
| service | CommunityService.class |
| service | ExceEvalService.class |
| service | MonthlyReportService.class |
| service | MyHealthMainDashService.class |
| service | NutEvalService.class |
| service | RankService.class |
| service | ServeyService.class |
| impl | AfterServeyDAO.class |
| impl | AfterServeyServiceImpl.class |
| impl | CommunityDAO.class |
| impl | CommunityServiceImpl.class |
| impl | ExceEvalDAO.class |
| impl | ExceEvalServiceImpl.class |
| impl | MonthlyReportDAO.class |
| impl | MonthlyReportServiceImpl.class |
| impl | MyHealthMainDashDAO.class |
| impl | MyHealthMainDashServiceImpl.class |
| impl | NutEvalDAO.class |
| impl | NutEvalServiceImpl.class |
| impl | RankDAO.class |
| impl | RankServiceImpl.class |
| impl | ServeyDAO.class |
| impl | ServeyServiceImpl.class |

---

## 3. mhcweb — 웹 포털 (363개)

### cm (공통관리) — 34개

**controller/ (10개)**
| # | 파일명 |
|---|--------|
| 1 | EduVideosMngtController.class |
| 2 | ExcsCodeMngtController.class |
| 3 | HealthExamReqController.class |
| 4 | MainController.class |
| 5 | MobileNoticeMngtController.class |
| 6 | NoticeMngtController.class |
| 7 | NoticeMngtController_Org.class |
| 8 | NutriCodeMngtController.class |
| 9 | PageNaviController.class |
| 10 | PushController.class |
| 11 | SampleController.class |

**service/ (9개)**
| # | 파일명 |
|---|--------|
| 1 | EduVideosMngtService.class |
| 2 | ExcsCodeMngtService.class |
| 3 | HealthExamReqService.class |
| 4 | MainService.class |
| 5 | MobileNoticeMngtService.class |
| 6 | NoticeMngtService.class |
| 7 | NoticeMngtService_Org.class |
| 8 | NutriCodeMngtService.class |
| 9 | PushService.class |

**service/impl/ (16개)**
| # | 파일명 |
|---|--------|
| 1 | EduVideosMngtDAO.class |
| 2 | EduVideosMngtServiceImpl.class |
| 3 | ExcsCodeMngtServiceDAO.class |
| 4 | ExcsCodeMngtServiceImpl.class |
| 5 | HealthExamReqDAO.class |
| 6 | HealthExamReqServiceImpl.class |
| 7 | MainDAO.class |
| 8 | MainServiceImpl.class |
| 9 | MobileNoticeMngtDAO.class |
| 10 | MobileNoticeMngtServiceImpl.class |
| 11 | NoticeMngtDAO.class |
| 12 | NoticeMngtDAO_Org.class |
| 13 | NoticeMngtServiceImpl.class |
| 14 | NoticeMngtServiceImpl_Org.class |
| 15 | NutriCodeMngtServiceDAO.class |
| 16 | NutriCodeMngtServiceImpl.class |
| 17 | PushDAO.class |
| 18 | PushServiceImpl.class |

---

### gn (일반/네비게이션) — 30개

**controller/ (10개)**
| # | 파일명 |
|---|--------|
| 1 | GnMainController.class |
| 2 | GnrlCmntyGSetController.class |
| 3 | GnrlGroupMngtController.class |
| 4 | GnrlMealDiaryRegController.class |
| 5 | GnrlMissionCodeMngtController.class |
| 6 | GnrlMissionMngtController.class |
| 7 | GnrlPointRankingController.class |
| 8 | GnrlSelfMissionMonitoringController.class |
| 9 | GnrlSvcMngtController.class |
| 10 | GnrlUserInfoMngtController.class |

**service/ (10개)**
| # | 파일명 |
|---|--------|
| 1 | GnMainService.class |
| 2 | GnrlCmntyGSetService.class |
| 3 | GnrlGroupMngtService.class |
| 4 | GnrlMealDiaryRegService.class |
| 5 | GnrlMissionCodeMngtService.class |
| 6 | GnrlMissionMngtService.class |
| 7 | GnrlPointRankingService.class |
| 8 | GnrlSelfMissionMonitoringService.class |
| 9 | GnrlSvcMngtService.class |
| 10 | GnrlUserInfoMngtService.class |

**service/impl/ (20개)**
| # | 파일명 |
|---|--------|
| 1 | GnMainDAO.class |
| 2 | GnMainServiceImpl.class |
| 3 | GnrlCmntyGSetDAO.class |
| 4 | GnrlCmntyGSetServiceImpl.class |
| 5 | GnrlGroupMngtDAO.class |
| 6 | GnrlGroupMngtServiceImpl.class |
| 7 | GnrlMealDiaryRegDAO.class |
| 8 | GnrlMealDiaryRegServiceImpl.class |
| 9 | GnrlMissionCodeMngtDAO.class |
| 10 | GnrlMissionCodeMngtServiceImpl.class |
| 11 | GnrlMissionMngtDAO.class |
| 12 | GnrlMissionMngtServiceImpl.class |
| 13 | GnrlPointRankingDAO.class |
| 14 | GnrlPointRankingServiceImpl.class |
| 15 | GnrlSelfMissionMonitoringDAO.class |
| 16 | GnrlSelfMissionMonitoringImpl.class |
| 17 | GnrlSvcMngtDAO.class |
| 18 | GnrlSvcMngtServiceImpl.class |
| 19 | GnrlUserInfoMngtDAO.class |
| 20 | GnrlUserInfoMngtServiceImpl.class |

---

### mr (경영보고) — 42개

**controller/ (14개)**
| # | 파일명 |
|---|--------|
| 1 | ConcCnslInfoController.class |
| 2 | ExcsRecController.class |
| 3 | HealthDisorderInfoController.class |
| 4 | InputHealthInfoCnfmController.class |
| 5 | MealDiaryRegController.class |
| 6 | MealRegController.class |
| 7 | MthlyHealthRptSndSttsController.class |
| 8 | PointRankingController.class |
| 9 | RankPymntController.class |
| 10 | SportActivityController.class |
| 11 | SvcJoinInfoController.class |
| 12 | SvcJoinListController.class |
| 13 | SvcJoinRateController.class |
| 14 | VisitExptController.class |

**service/ (14개)**
| # | 파일명 |
|---|--------|
| 1 | ConcCnslInfoService.class |
| 2 | ExcsRecService.class |
| 3 | HealthDisorderInfoService.class |
| 4 | InputHealthInfoCnfmService.class |
| 5 | MealDiaryRegService.class |
| 6 | MealRegService.class |
| 7 | MthlyHealthRptSndSttsService.class |
| 8 | PointRankingService.class |
| 9 | RankPymntService.class |
| 10 | SportActivityService.class |
| 11 | SvcJoinInfoService.class |
| 12 | SvcJoinListService.class |
| 13 | SvcJoinRateService.class |
| 14 | VisitExptService.class |

**service/impl/ (28개)**
| # | 파일명 |
|---|--------|
| 1 | ConcCnslInfoDAO.class |
| 2 | ConcCnslInfoServiceImpl.class |
| 3 | ExcsRecDAO.class |
| 4 | ExcsRecServiceImpl.class |
| 5 | HealthDisorderInfoDAO.class |
| 6 | HealthDisorderInfoServiceImpl.class |
| 7 | InputHealthInfoCnfmDAO.class |
| 8 | InputHealthInfoCnfmServiceImpl.class |
| 9 | MealDiaryRegDAO.class |
| 10 | MealDiaryRegServiceImpl.class |
| 11 | MealRegDAO.class |
| 12 | MealRegServiceImpl.class |
| 13 | MthlyHealthRptSndSttsDAO.class |
| 14 | MthlyHealthRptSndSttsServiceImpl.class |
| 15 | PointRankingDAO.class |
| 16 | PointRankingServiceImpl.class |
| 17 | RankPymntDAO.class |
| 18 | RankPymntServiceImpl.class |
| 19 | SportActivityDAO.class |
| 20 | SportActivityServiceImpl.class |
| 21 | SvcJoinInfoDAO.class |
| 22 | SvcJoinInfoServiceImpl.class |
| 23 | SvcJoinListDAO.class |
| 24 | SvcJoinListServiceImpl.class |
| 25 | SvcJoinRateDAO.class |
| 26 | SvcJoinRateServiceImpl.class |
| 27 | VisitExptDAO.class |
| 28 | VisitExptServiceImpl.class |

---

### ms (모바일서비스) — 4개

| 계층 | 파일명 |
|------|--------|
| controller | MeasrMastrController.class |
| service | MeasrMastrService.class |
| impl | MeasrMastrDAO.class |
| impl | MeasrMastrServiceImpl.class |

---

### pm (성과관리) — 21개

**controller/ (7개)**
| # | 파일명 |
|---|--------|
| 1 | AppServiceUseSttusController.class |
| 2 | DeviceUserRateSttusController.class |
| 3 | IntensiveUseSttusController.class |
| 4 | MajorResultIndexController.class |
| 5 | TrgterProcSttusController.class |
| 6 | TrgterRegSttusController.class |
| 7 | WorkManagerSttusController.class |

**service/ (7개)**
| # | 파일명 |
|---|--------|
| 1 | AppServiceUseSttusService.class |
| 2 | DeviceUserRateSttusService.class |
| 3 | IntensiveUseSttusService.class |
| 4 | MajorResultIndexService.class |
| 5 | TrgterProcSttusService.class |
| 6 | TrgterRegSttusService.class |
| 7 | WorkManagerSttusService.class |

**service/impl/ (14개)**
| # | 파일명 |
|---|--------|
| 1 | AppServiceUseSttusDAO.class |
| 2 | AppServiceUseSttusServiceImpl.class |
| 3 | DeviceUserRateSttusDAO.class |
| 4 | DeviceUserRateSttusServiceImpl.class |
| 5 | IntensiveUseSttusDAO.class |
| 6 | IntensiveUseSttusServiceImpl.class |
| 7 | MajorResultIndexDAO.class |
| 8 | MajorResultIndexServiceImpl.class |
| 9 | TrgterProcSttusDAO.class |
| 10 | TrgterProcSttusServiceImpl.class |
| 11 | TrgterRegSttusDAO.class |
| 12 | TrgterRegSttusServiceImpl.class |
| 13 | WorkManagerSttusDAO.class |
| 14 | WorkManagerSttusServiceImpl.class |

---

### sample — 4개

| 계층 | 파일명 |
|------|--------|
| web | TestController.class |
| service | TestService.class |
| impl | TestDAO.class |
| impl | TestServiceImpl.class |

---

### sm (표본관리) — 25개

**controller/ (8개)**
| # | 파일명 |
|---|--------|
| 1 | BoardController.class |
| 2 | HealthDisValMngtController.class |
| 3 | MngterRegMngtController.class |
| 4 | OpenApiMngtController.class |
| 5 | OrgMngtController.class |
| 6 | OrgMngtMonitoringController.class |
| 7 | ServeyReSearchMngtController.class |
| 8 | ServiceRequestMngtController.class |

**service/ (8개)**
| # | 파일명 |
|---|--------|
| 1 | BoardService.class |
| 2 | HealthDisValMngtService.class |
| 3 | MngterRegMngtService.class |
| 4 | OpenApiMngtService.class |
| 5 | OrgMngtMonitoringService.class |
| 6 | OrgMngtService.class |
| 7 | ServeyReSearchMngtService.class |
| 8 | ServiceRequestMngtService.class |

**service/impl/ (17개)**
| # | 파일명 |
|---|--------|
| 1 | BoardDAO.class |
| 2 | BoardServiceImpl.class |
| 3 | HealthDisValMngtServiceDAO.class |
| 4 | HealthDisValMngtServiceImpl.class |
| 5 | MngterRegMngtServiceDAO.class |
| 6 | MngterRegMngtServiceImpl.class |
| 7 | OpenApiMngtDAO.class |
| 8 | OpenApiMngtServiceImpl.class |
| 9 | OrgMngtDAO.class |
| 10 | OrgMngtMonitoringDAO.class |
| 11 | OrgMngtMonitoringServiceImpl.class |
| 12 | OrgMngtServiceImpl.class |
| 13 | ServeyReSearchMngtDAO.class |
| 14 | ServeyReSearchMngtServiceImpl.class |
| 15 | ServeyReSearchMngtServiceImpl$1.class |
| 16 | ServiceRequestMngtDAO.class |
| 17 | ServiceRequestMngtServiceImpl.class |

---

### st (통계) — 21개

**controller/ (7개)**
| # | 파일명 |
|---|--------|
| 1 | CnslSttusController.class |
| 2 | MonitoringController.class |
| 3 | MthlyHealthRptController.class |
| 4 | RecSentMngtController.class |
| 5 | StatisticsExcelController.class |
| 6 | TrgterSttusController.class |
| 7 | WorkHistInfoController.class |

**service/ (7개)**
| # | 파일명 |
|---|--------|
| 1 | CnslSttusService.class |
| 2 | MonitoringService.class |
| 3 | MthlyHealthRptService.class |
| 4 | RecSentMngtService.class |
| 5 | StatisticsExcelService.class |
| 6 | TrgterSttusService.class |
| 7 | WorkHistInfoService.class |

**service/impl/ (14개)**
| # | 파일명 |
|---|--------|
| 1 | CnslSttusServiceDAO.class |
| 2 | CnslSttusServiceImpl.class |
| 3 | MonitoringServiceDAO.class |
| 4 | MonitoringServiceImpl.class |
| 5 | MthlyHealthRptServiceDAO.class |
| 6 | MthlyHealthRptServiceImpl.class |
| 7 | RecSentMngtDAO.class |
| 8 | RecSentMngtServiceImpl.class |
| 9 | StatisticsExcelServiceDAO.class |
| 10 | StatisticsExcelServiceImpl.class |
| 11 | TrgterSttusServiceDAO.class |
| 12 | TrgterSttusServiceImpl.class |
| 13 | WorkHistInfoServiceDAO.class |
| 14 | WorkHistInfoServiceImpl.class |

---

### sv (서비스관리) — 48개

**controller/ (16개)**
| # | 파일명 |
|---|--------|
| 1 | CmntyGSetController.class |
| 2 | CnslReqMngController.class |
| 3 | ComnCnslMngtController.class |
| 4 | ConcCnslMngtController.class |
| 5 | IntensiveBodyActObstyCnslController.class |
| 6 | IntensiveCnslMngtController.class |
| 7 | MissionCodeMngtController.class |
| 8 | MissionMngtController.class |
| 9 | NoticeSetMngController.class |
| 10 | PractMissonCntntsController.class |
| 11 | PractMissonSchMngtController.class |
| 12 | SmsMngController.class |
| 13 | SvcBgnAppointController.class |
| 14 | SvcMngtController.class |
| 15 | VisitSchResrvtMngtController.class |
| 16 | WeekSchMngtController.class |

**service/ (16개)**
| # | 파일명 |
|---|--------|
| 1 | CmntyGSetService.class |
| 2 | CnslReqMngService.class |
| 3 | ComnCnslMngtService.class |
| 4 | ConcCnslMngtService.class |
| 5 | IntensiveBodyActObstyCnslService.class |
| 6 | IntensiveCnslMngtService.class |
| 7 | MissionCodeMngtService.class |
| 8 | MissionMngtService.class |
| 9 | NoticeSetMngService.class |
| 10 | PractMissonCntntsService.class |
| 11 | PractMissonSchMngtService.class |
| 12 | SmsMngService.class |
| 13 | SvcBgnAppointService.class |
| 14 | SvcMngtService.class |
| 15 | VisitSchResrvtMngtService.class |
| 16 | WeekSchMngtService.class |

**service/impl/ (32개)**
| # | 파일명 |
|---|--------|
| 1 | CmntyGSetDAO.class |
| 2 | CmntyGSetServiceImpl.class |
| 3 | CnslReqMngServiceDAO.class |
| 4 | CnslReqMngServiceImpl.class |
| 5 | ComnCnslMngtServiceDAO.class |
| 6 | ComnCnslMngtServiceImpl.class |
| 7 | ConcCnslMngtServiceDAO.class |
| 8 | ConcCnslMngtServiceImpl.class |
| 9 | IntensiveBodyActObstyCnslDAO.class |
| 10 | IntensiveBodyActObstyCnslServiceImpl.class |
| 11 | IntensiveCnslMngtServiceDAO.class |
| 12 | IntensiveCnslMngtServiceImpl.class |
| 13 | MissionCodeMngtDAO.class |
| 14 | MissionCodeMngtServiceImpl.class |
| 15 | MissionMngtDAO.class |
| 16 | MissionMngtServiceImpl.class |
| 17 | NoticeSetMngServiceDAO.class |
| 18 | NoticeSetMngServiceImpl.class |
| 19 | PractMissonCntntsServiceDAO.class |
| 20 | PractMissonCntntsServiceImpl.class |
| 21 | PractMissonSchMngtServiceDAO.class |
| 22 | PractMissonSchMngtServiceImpl.class |
| 23 | SmsMngDAO.class |
| 24 | SmsMngServiceImpl.class |
| 25 | SvcBgnAppointDAO.class |
| 26 | SvcBgnAppointServiceImpl.class |
| 27 | SvcMngtDAO.class |
| 28 | SvcMngtServiceImpl.class |
| 29 | VisitSchResrvtMngtServiceDAO.class |
| 30 | VisitSchResrvtMngtServiceImpl.class |
| 31 | WeekSchMngtDAO.class |
| 32 | WeekSchMngtServiceImpl.class |

---

### tg (대상자관리) — 48개

**controller/ (16개)**
| # | 파일명 |
|---|--------|
| 1 | BodyActObstyCnslController.class |
| 2 | CallingMaterialController.class |
| 3 | DeviceDistrbtMngtController.class |
| 4 | HealthExamMngtController.class |
| 5 | HealthMngtCnslController.class |
| 6 | MeasrDataMngtController.class |
| 7 | PhisCnctTrgterCurController.class |
| 8 | PhisPreTrgterMngtController.class |
| 9 | PreTrgterMngtController.class |
| 10 | ServiceObjMngtController.class |
| 11 | SvcJoinMngtController.class |
| 12 | TrgterAftMngtController.class |
| 13 | TrgterCmplMngtController.class |
| 14 | TrgterDropMngtController.class |
| 15 | TrgterFormMngtcontroller.class |
| 16 | TrgterInfoMngtController.class |

**service/ (16개)**
| # | 파일명 |
|---|--------|
| 1 | BodyActObstyCnslService.class |
| 2 | CallingMaterialService.class |
| 3 | DeviceDistrbtMngtService.class |
| 4 | HealthExamMngtService.class |
| 5 | HealthMngtCnslService.class |
| 6 | MeasrDataMngtService.class |
| 7 | PhisCnctTrgterCurService.class |
| 8 | PhisPreTrgterMngtService.class |
| 9 | PreTrgterMngtService.class |
| 10 | ServiceObjMngtService.class |
| 11 | SvcJoinMngtService.class |
| 12 | TrgterAftMngtService.class |
| 13 | TrgterCmplMngtService.class |
| 14 | TrgterDropMngtService.class |
| 15 | TrgterFormMngtService.class |
| 16 | TrgterInfoMngtService.class |

**service/impl/ (32개)**
| # | 파일명 |
|---|--------|
| 1 | BodyActObstyCnslDAO.class |
| 2 | BodyActObstyCnslServiceImpl.class |
| 3 | CallingMaterialServiceDAO.class |
| 4 | CallingMaterialServiceImpl.class |
| 5 | DeviceDistrbtMngtServiceDAO.class |
| 6 | DeviceDistrbtMngtServiceImpl.class |
| 7 | HealthExamMngtDAO.class |
| 8 | HealthExamMngtServiceImpl.class |
| 9 | HealthMngtCnslDAO.class |
| 10 | HealthMngtCnslServiceImpl.class |
| 11 | MeasrDataMngtDAO.class |
| 12 | MeasrDataMngtServiceImpl.class |
| 13 | PhisCnctTrgterCurDAO.class |
| 14 | PhisCnctTrgterCurServiceImpl.class |
| 15 | PhisPreTrgterMngtDAO.class |
| 16 | PhisPreTrgterMngtServiceImpl.class |
| 17 | PreTrgterMngtServiceDAO.class |
| 18 | PreTrgterMngtServiceImpl.class |
| 19 | ServiceObjMngtServiceDAO.class |
| 20 | ServiceObjMngtServiceImpl.class |
| 21 | SvcJoinMngtDAO.class |
| 22 | SvcJoinMngtServiceImpl.class |
| 23 | TrgterAftMngtDAO.class |
| 24 | TrgterAftMngtServiceImpl.class |
| 25 | TrgterCmplMngtDAO.class |
| 26 | TrgterCmplMngtServiceImpl.class |
| 27 | TrgterDropMngtDAO.class |
| 28 | TrgterDropMngtServiceImpl.class |
| 29 | TrgterFormMngtServiceDAO.class |
| 30 | TrgterFormMngtServiceImpl.class |
| 31 | TrgterInfoMngtDAO.class |
| 32 | TrgterInfoMngtServiceImpl.class |

---

## 요약 통계

| 영역 | 모듈 | 파일 수 |
|------|------|---------|
| **common** | 공통 레이어 | 50 |
| **mhcapp** | cm (공통) | 4 |
| **mhcapp** | mr (경영보고) | 4 |
| **mhcapp** | sv (서비스) | 28 |
| **mhcweb** | cm (공통관리) | 34 |
| **mhcweb** | gn (일반/네비게이션) | 30 |
| **mhcweb** | mr (경영보고) | 42 |
| **mhcweb** | ms (모바일서비스) | 4 |
| **mhcweb** | pm (성과관리) | 21 |
| **mhcweb** | sample | 4 |
| **mhcweb** | sm (표본관리) | 25 |
| **mhcweb** | st (통계) | 21 |
| **mhcweb** | sv (서비스관리) | 48 |
| **mhcweb** | tg (대상자관리) | 48 |
| | **합계** | **449** |
